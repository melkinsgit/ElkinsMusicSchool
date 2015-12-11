package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/7/2015.
 */
public class StudentGUI extends JPanel{
    private JPanel StudentGUITab;
    private JButton addStudentButton;
    private JTextArea studResultsTextArea;
    private JTextArea studErrorTextArea;
    private JButton quitButton;
    private JComboBox allStudentsComboBox;
    private JTextField studentFirstNameTextField;
    private JTextField studentLastNameTextField;
    private JTextField studentPhoneTextField;

    private JLabel errorMessagesLabel;
    private JLabel showStudentSkedLabel;
    private JLabel outputLabel;
    private JLabel enterStudentFirstNameLabel;
    private JLabel enterStudentLastNameLabel;
    private JLabel enterStudentPhoneLabel;
    private JLabel addStudentLabel;

    protected String studentFirstToAdd;
    protected String studentLastToAdd;
    protected String studentPhoneToAdd;

    private String textInputError;
    Student student = new Student();

    public StudentGUI () {

        studErrorTextArea.setLineWrap(true);
        studErrorTextArea.setEditable(false);
        studResultsTextArea.setLineWrap(true);
        studResultsTextArea.setEditable(false);

        // put all student names in the combo box
        String start = "";
        allStudentsComboBox.addItem(start);
        String studentInComboBox;
        try {
            ResultSet comboBoxRS = student.AllDataQuery();
            while (comboBoxRS.next()) {
                studentInComboBox = (comboBoxRS.getString(CreateTables.STUDENT_FIRST_COLUMN)) + " " + (comboBoxRS.getString(CreateTables.STUDENT_LAST_COLUMN));
                allStudentsComboBox.addItem(studentInComboBox);
            }
            comboBoxRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("Adding text to combo box " + sqle);
        }

        // quit code - taken from Clara's Movie Ratings project
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                MovieDatabase.shutdown();  // TODO shut down proper database
                System.exit(0);   // TODO Should probably be a call back to Main class so all the System.exit(0) calls are in one place.
            }
        });

        // TODO validate user input so no BLANKS!
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studResultsTextArea.setText("");
                AddStudent();
            }
        });

        //
        allStudentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studErrorTextArea.setText("");
                Object studentToSked = allStudentsComboBox.getSelectedItem();
                String studentToSkedStr = (String) studentToSked;
                boolean OKToShow = false;
                Student student = new Student();
                if (!studentToSkedStr.equals("")){
                    OKToShow = true;
                }
                else {
                    studErrorTextArea.setText("That is not a valid choice. Please choose a student name from the drop down menu.");
                    studResultsTextArea.setText("");
                }
                if (OKToShow) {
                    SkedInputOutput(studentToSkedStr);
                }
            }
        });

        studentFirstNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                studErrorTextArea.setText("");
                allStudentsComboBox.setSelectedIndex(0);
            }
        });
        studentLastNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                studErrorTextArea.setText("");
            }
        });
        studentPhoneTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                studErrorTextArea.setText("");
            }
        });
    }

    public JPanel getPanel() { return StudentGUITab;}

    private void AddStudent () {
        boolean OKToAdd = false;
        Student studentToAdd= new Student();
        studentFirstToAdd = studentFirstNameTextField.getText();
        studentLastToAdd = studentLastNameTextField.getText();
        studentPhoneToAdd = studentPhoneTextField.getText();
        if (Queries.IsValidDBString(studentFirstToAdd) && Queries.IsValidDBString(studentLastToAdd)) {
            if (Queries.phoneInputMsg(studentPhoneToAdd).equals("")) {
                OKToAdd = true;
            }
            else {
                studErrorTextArea.setText(Queries.phoneInputMsg(studentPhoneToAdd));
            }
        } else {
            textInputError = "You must enter a value for first and last name. Please re-enter try again and click Add This Student.";
            studErrorTextArea.setText(textInputError + " " + Queries.phoneInputMsg(studentPhoneToAdd));
        }
        if (OKToAdd) {
            studentToAdd.setFirstName(studentFirstToAdd);
            studentToAdd.setLastName(studentLastToAdd);
            studentToAdd.setPhone(studentPhoneToAdd);
            studentToAdd.AddStudent(studentToAdd);
            studResultsTextArea.setText(studentFirstToAdd + " " + studentLastToAdd + " has been added.");
            studentFirstNameTextField.setText("");
            studentLastNameTextField.setText("");
            studentPhoneTextField.setText("");
        }
    }

    private void SkedInputOutput (String studentToSkedStr){
        Student studentforSked = new Student();
        try {
            ResultSet studSkedRS = studentforSked.GetSchedule(studentToSkedStr);
            String stringToDisplay = "";
            if (Queries.GetRowCount(studSkedRS) == 0){
                stringToDisplay = "That student is not enrolled in any classes.";
                studResultsTextArea.setText(stringToDisplay);
            }
            else if (Queries.GetRowCount(studSkedRS) == 1) {
                studSkedRS.next();
                stringToDisplay = (studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                studSkedRS.beforeFirst();
                studResultsTextArea.setText(stringToDisplay);
            }
            else {
                studSkedRS.next();
                stringToDisplay = (studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                studResultsTextArea.setText(stringToDisplay);
                while (studSkedRS.next()) {
                    stringToDisplay = ("\n" + (studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN)));
                    studResultsTextArea.append(stringToDisplay);
                }
                studSkedRS.beforeFirst();
            }
            studSkedRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("In display search results " + sqle);
        }
    }

    public void setStudResultsTextArea(String studResultsTextArea) {
        this.studResultsTextArea.setText(studResultsTextArea);
    }

    public void setStudErrorTextArea(String studErrorTextArea) {
        this.studErrorTextArea.setText(studErrorTextArea);
    }

    public void setAllStudentsComboBox(JComboBox allStudentsComboBox) {
        this.allStudentsComboBox = allStudentsComboBox;
    }

    public void setStudentFirstNameTextField(String studentFirstNameTextField) {
        this.studentFirstNameTextField.setText(studentFirstNameTextField);
    }

    public void setStudentLastNameTextField(String studentLastNameTextField) {
        this.studentLastNameTextField.setText(studentLastNameTextField);
    }

    public void setTextInputError(String textInputError) {
        this.textInputError = textInputError;
    }

    public JComboBox getAllStudentsComboBox() {
        return allStudentsComboBox;
    }

    public String getStudentFirstNameTextField() {
        return studentFirstNameTextField.getText();
    }

    public String getStudentLastNameTextField() {
        return studentLastNameTextField.getText();
    }

    public String getStudentPhoneTextField() {
        return studentPhoneTextField.getText();
    }
}
