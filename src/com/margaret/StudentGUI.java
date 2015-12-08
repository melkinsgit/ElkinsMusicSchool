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
        studResultsTextArea.setLineWrap(true);

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
//                MovieDatabase.shutdown();
                System.exit(0);   //Should probably be a call back to Main class so all the System.exit(0) calls are in one place.
            }
        });

        // TODO validate user input so no BLANKS!
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                System.out.println("the student chosen for schedule is " + studentToSkedStr);
                Student student = new Student();
                if (!studentToSkedStr.equals("")){
                    OKToShow = true;
                }
                else {
                    studErrorTextArea.setText("That is not a valid choice. Please choose a student name from the drop down menu.");
                }
                if (OKToShow) {
                    DisplaySked(studentToSkedStr);
                }
            }
        });

        studentFirstNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                studErrorTextArea.setText("");
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
        studentFirstToAdd = studentFirstNameTextField.getText();
        studentLastToAdd = studentLastNameTextField.getText();
        studentPhoneToAdd = studentPhoneTextField.getText();
        System.out.println("The student to add is " + studentFirstToAdd + " " + studentLastToAdd + " " + studentPhoneToAdd);
        if (Queries.IsValidDBString(studentFirstToAdd) && Queries.IsValidDBString(studentLastToAdd)) {
            System.out.println("passed first and last name string tests");
            if (Queries.phoneInputMsg(studentPhoneToAdd).equals("")) {
                System.out.println("Passed phone test");
                OKToAdd = true;
            }
            else {
                studErrorTextArea.setText(Queries.phoneInputMsg(studentPhoneToAdd));
            }
        } else {
            System.out.println("didn't pass first and last name string tests");
            textInputError = "You must enter a value for first and last name. Please re-enter try again and click Add This Student.";
            System.out.println("the error message will be " + textInputError + " " + Queries.phoneInputMsg(studentPhoneToAdd));
            studErrorTextArea.setText(textInputError + " " + Queries.phoneInputMsg(studentPhoneToAdd));
        }
        if (OKToAdd) {
            student.AddStudent(studentFirstToAdd, studentLastToAdd, studentPhoneToAdd);
            studResultsTextArea.setText(studentFirstToAdd + " " + studentLastToAdd + " has been added.");
            studentFirstNameTextField.setText("");
            studentLastNameTextField.setText("");
            studentPhoneTextField.setText("");
        }
    }

    private void DisplaySked (String studentToSkedStr){
        System.out.println("the student chosen for schedule is " + studentToSkedStr);
        Student student = new Student();
        try {
            ResultSet studSkedRS = student.ShowSchedule(studentToSkedStr);
            String stringToDisplay = "";
            if (Queries.GetRowCount(studSkedRS) == 0){
                System.out.println("Got 0 as true");
                stringToDisplay = "That student is not enrolled in any classes.";
            }
            else if (Queries.GetRowCount(studSkedRS) == 1) {
                studSkedRS.next();
                stringToDisplay = (studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                studSkedRS.beforeFirst();
            }
            else {
                studSkedRS.next();
                System.out.println("In the else");
                stringToDisplay = (studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                System.out.println("about to go into while loop and the string is " + stringToDisplay);
                while (studSkedRS.next()) {
                    stringToDisplay = stringToDisplay + "\n" + ((studSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (studSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + studSkedRS.getString(CreateTables.CLASS_TIME_COLUMN)));
                    System.out.println("the result sked is " + stringToDisplay);
                }
                studSkedRS.beforeFirst();
            }
            studResultsTextArea.setText(stringToDisplay);
            studSkedRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("In display search results " + sqle);
        }
    }
}
