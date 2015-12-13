package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/8/2015.
 */
public class TeacherGUI {

    private JPanel TeacherGUITab;
    private JTextField teacherFirstNameTextField;
    private JTextField teacherLastNameTextField;
    private JTextField teacherPhoneTextField;
    private JButton addTeacherButton;
    private JTextArea teachResultTextArea;
    private JTextArea teachErrorTextArea;
    private JLabel addTeacherLabel;
    private JLabel enterTeacherFirstNameLabel;
    private JLabel enterTeacherLastNameLabel;
    private JLabel enterTeacherPhoneLabel;
    private JButton quitButton;
    private JLabel teachErrorLabel;
    private JLabel teachResultLabel;
    private JComboBox allTeachersComboBox;
    private JLabel showSkedLabel;

    protected String teacherFirstToAdd;
    protected String teacherLastToAdd;
    protected String teacherPhoneToAdd;

    private String textInputError;
    Student student = new Student();
    Teacher teacher = new Teacher();
    String start = "";
    boolean OKToShow = false;

    public TeacherGUI () {

        teachErrorTextArea.setLineWrap(true);
        teachErrorTextArea.setEditable(false);
        teachResultTextArea.setLineWrap(true);
        teachResultTextArea.setEditable(false);

        setTeacherComboBox();

        teacherFirstNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachErrorTextArea.setText("");
                teachResultTextArea.setText("");
            }
        });
        teacherLastNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachErrorLabel.setText("");
                teachResultTextArea.setText("");
            }
        });
        teacherPhoneTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachErrorTextArea.setText("");
                teachResultTextArea.setText("");
            }
        });


        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachResultTextArea.setText("");
                AddTeacher();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                MovieDatabase.shutdown();  // TODO shut down proper database
                System.exit(0);   // TODO Should probably be a call back to Main class so all the System.exit(0) calls are in one place.
            }
        });

        allTeachersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachErrorTextArea.setText("");
                Object teacherToSked = allTeachersComboBox.getSelectedItem();
                String teacherToSkedStr = (String) teacherToSked;
//                Student student = new Student();
                if (!teacherToSkedStr.equals("")){
                    OKToShow = true;
                }
                else {
                    teachErrorTextArea.setText("That is not a valid choice. Please choose a student name from the drop down menu.");
                    teachResultTextArea.setText("");
                }
                if (OKToShow) {
                    SkedInputOutput(teacherToSkedStr);
                    OKToShow = false;
                }
            }
        });
    }

    private void addOneToComboBox(String teacherFirstToAdd, String teacherLastToAdd) {
        ResultSet getRecordRS;
        String getRecordStr = "SELECT * FROM " + CreateTables.TEACHER_TABLE_NAME + " WHERE " + CreateTables.TEACHER_FIRST_COLUMN + " LIKE '" + teacherFirstToAdd + "' AND " + CreateTables.TEACHER_LAST_COLUMN + " LIKE '" + teacherLastToAdd + "'";
        System.out.println("this query may not be working " + getRecordStr);
        try {
            getRecordRS = ConnectDB.statement.executeQuery(getRecordStr);
            while (getRecordRS.next()) {
                String updateTeacherInComboBox = (getRecordRS.getString(CreateTables.TEACHER_PK_COL) + " " + getRecordRS.getString(CreateTables.TEACHER_FIRST_COLUMN) + " " + getRecordRS.getString(CreateTables.TEACHER_LAST_COLUMN));
                allTeachersComboBox.addItem(updateTeacherInComboBox);
            }
        }
        catch (SQLException sqle){
            System.out.println("In adding teacher to Combo " + sqle);
        }
    }

    private void SkedInputOutput (String teacherToSkedStr){
        Teacher teacherforSked = new Teacher();
        try {
            ResultSet teachSkedRS = teacherforSked.GetSchedule(teacherToSkedStr);
            String stringToDisplay = "";
            if (Queries.GetRowCount(teachSkedRS) == 0){
                stringToDisplay = teacherToSkedStr.substring(teacherToSkedStr.indexOf(" ")) + " is not teaching any classes.";
                teachResultTextArea.setText(stringToDisplay);
            }
            else if (Queries.GetRowCount(teachSkedRS) == 1) {
                teachSkedRS.next();
                stringToDisplay = (teachSkedRS.getString(CreateTables.CLASS_NAME_COLUMN) + " " + teachSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + teachSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                teachSkedRS.beforeFirst();
                teachResultTextArea.setText(stringToDisplay);
            }
            else {
                teachSkedRS.next();
                stringToDisplay = (teachSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (teachSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + teachSkedRS.getString(CreateTables.CLASS_TIME_COLUMN));
                teachResultTextArea.setText(stringToDisplay);
                while (teachSkedRS.next()) {
                    stringToDisplay = ("\n" + (teachSkedRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (teachSkedRS.getString(CreateTables.CLASS_DAY_COLUMN) + " " + teachSkedRS.getString(CreateTables.CLASS_TIME_COLUMN)));
                    teachResultTextArea.append(stringToDisplay);
                }
                teachSkedRS.beforeFirst();
            }
            teachSkedRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("In display search results " + sqle);
        }
    }

    private void AddTeacher () {
        boolean OKToAdd = false;
        Teacher teacherToAdd = new Teacher();
        teacherFirstToAdd = teacherFirstNameTextField.getText();
        teacherLastToAdd = teacherLastNameTextField.getText();
        teacherPhoneToAdd = teacherPhoneTextField.getText();
        if (Queries.IsValidDBString(teacherFirstToAdd) && Queries.IsValidDBString(teacherLastToAdd)) {
            if (Queries.phoneInputMsg(teacherPhoneToAdd).equals("")) {
                OKToAdd = true;
            }
            else {
                teachErrorTextArea.setText(Queries.phoneInputMsg(teacherPhoneToAdd));
            }
        } else {
            textInputError = "You must enter a value for first and last name. Please re-enter try again and click Add This Student.";
            teachErrorTextArea.setText(textInputError + " " + Queries.phoneInputMsg(teacherPhoneToAdd));
        }
        if (OKToAdd) {
            teacherToAdd.setFirstName(teacherFirstToAdd);
            teacherToAdd.setLastName(teacherLastToAdd);
            teacherToAdd.setPhone(teacherPhoneToAdd);
            teacherToAdd.AddTeacher();
            teachResultTextArea.setText(teacherFirstToAdd + " " + teacherLastToAdd + " has been added.");
            teacherFirstNameTextField.setText("");
            teacherLastNameTextField.setText("");
            teacherPhoneTextField.setText("");
            addOneToComboBox(teacherFirstToAdd, teacherLastToAdd);
        }
    }

//    private void AddTeacher() {
//    }

    public JPanel getPanel() { return TeacherGUITab;}

    private void setTeacherComboBox() {
        allTeachersComboBox.addItem(start);
        String teachersInComboBox;
        try {
            ResultSet teachersComboBoxRS;
            teachersComboBoxRS = teacher.AllDataQuery();
            while (teachersComboBoxRS.next()){
                teachersInComboBox = (teachersComboBoxRS.getString(CreateTables.TEACHER_PK_COL) + " " + teachersComboBoxRS.getString(CreateTables.TEACHER_FIRST_COLUMN) + " " + teachersComboBoxRS.getString(CreateTables.TEACHER_LAST_COLUMN));
                allTeachersComboBox.addItem(teachersInComboBox);
            }
        }
        catch (SQLException sqle){
            System.out.println("In set teacher combo box " + sqle);
        }
    }


}
