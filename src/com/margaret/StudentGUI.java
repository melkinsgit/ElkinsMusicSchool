package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/7/2015.
 */
public class StudentGUI extends JPanel{
    private JPanel StudentGUITab;
    private JButton addStudentButton;
    private JTextArea studResultsTextArea;
    private JButton quitButton;
    private JTextArea studErrorTextArea;
    private JComboBox allStudentsComboBox;
    private JLabel showStudentSkedLabel;
    private JLabel outputLabel;
    private JLabel errorMessagesLabel;
    private JTextField studentFirstNameTextField;
    private JTextField studentLastNameTextField;
    private JTextField studentPhoneTextField;
    private JLabel enterStudentFirstNameLabel;
    private JLabel enterStudentLastNameLabel;
    private JLabel enterStudentPhoneLabel;
    private JLabel addStudentLabel;
    protected String studentFirstToAdd;
    protected String studentLastToAdd;
    protected String studentPhoneToAdd;

    public StudentGUI () {

        // put all student names in the combo box
        String start = "";
        allStudentsComboBox.addItem(start);
        String studentInComboBox;
        Student student = new Student();
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

        allStudentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                MovieDatabase.shutdown();
                System.exit(0);   //Should probably be a call back to Main class so all the System.exit(0) calls are in one place.
            }
        });
        studentFirstNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentFirstToAdd = studentFirstNameTextField.getText();
            }
        });
        studentLastNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentLastToAdd = studentLastNameTextField.getText();
            }
        });

        studentPhoneTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPhoneToAdd = studentPhoneTextField.getText();
            }
        });
        // TODO verify user input so no BLANKS!
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentFirstToAdd = studentFirstNameTextField.getText();
                studentLastToAdd = studentLastNameTextField.getText();
                studentPhoneToAdd = studentPhoneTextField.getText();
//                Student student = new Student();
                student.AddStudent(studentFirstToAdd, studentLastToAdd, studentPhoneToAdd);
//                ResultSet studentsRS = student.AllDataQuery();
//                int studentPicked = student.DisplayAllStudents(studentsRS);
                studResultsTextArea.setText(studentFirstToAdd + " " + studentLastToAdd + " has been added.");
                studentFirstNameTextField.setText("");
                studentLastNameTextField.setText("");
                studentPhoneTextField.setText("");
            }
        });
        allStudentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object studentToSked = allStudentsComboBox.getSelectedItem();
                String studentToSkedStr = (String) studentToSked;
                System.out.println("the student chosen for schedule is " + studentToSkedStr);
                Student student = new Student();
//                ResultSet studentsRS = student.AllDataQuery();
//                int studentPicked = student.DisplayAllStudents(studentsRS);
                try {
                    ResultSet studSkedRS = student.ShowSchedule(studentToSkedStr);
//                    student.DisplayStudentSked(studSkedRS);
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
//                System.out.println("record in result set is " + rs.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + rs.getString(CreateTables.STUDENT_LAST_COLUMN));
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
        });
    }

    public JPanel getPanel() { return StudentGUITab;}
}
