package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/12/2015.
 */
public class EnrollGUI {
    private JPanel EnrollGUI;
    private JButton quitButton;
    private JButton enrollStudentButton;
    private JComboBox enrollStudentComboBox;
    private JLabel enrollInstrLabel;
    private JLabel studToEnrollLabel;
    private JComboBox classToEnrollComboBox;
    private JTextArea enrollResultsTextArea;
    private JTextField toEnrollAStudentTextField;
    private JTextArea enrollErrorTextArea;
    Student student = new Student();
    String studentToEnrollStr;
    String classToEnrollStr;
    boolean OKToShow = false;
    boolean OKToEnroll = false;

    public EnrollGUI() {

        setClassComboBox();
        setStudentComboBox();

        enrollErrorTextArea.setLineWrap(true);
        enrollErrorTextArea.setEditable(false);
        enrollResultsTextArea.setLineWrap(true);
        enrollResultsTextArea.setEditable(false);

        enrollStudentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        enrollStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        enrollStudentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this is for use in Class GUI

                enrollResultsTextArea.setText("");
                Object studentToSked = enrollStudentComboBox.getSelectedItem();
                studentToEnrollStr = (String) studentToSked;
                Student student = new Student();
                if (!studentToEnrollStr.equals("")){
                    OKToShow = true;
                }
                else {
                    enrollErrorTextArea.setText("That is not a valid choice. Please choose a student name from the drop down menu.");
                    enrollResultsTextArea.setText("");
                }
                if (OKToShow) {
                    ;
                }
            }
        });

        classToEnrollComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollErrorTextArea.setText("");
                Object classToEnroll = classToEnrollComboBox.getSelectedItem();
                classToEnrollStr = (String) classToEnroll;
                if (!classToEnrollStr.equals("")){
                    OKToEnroll = true;
                }
                else {
                    enrollErrorTextArea.setText("That is not a valid choice. Please choose a class from the drop down menu.");
                    enrollResultsTextArea.setText("");
                }
            }
        });


        enrollStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OKToShow && OKToEnroll){
                    MusicClass musicClass = new MusicClass();
                    musicClass.EnrollInClass(classToEnrollStr, studentToEnrollStr);
                    if (Queries.studentEnrolled) {
                        enrollResultsTextArea.setText(studentToEnrollStr + " has been enrolled in " + classToEnrollStr + ".");
                        Queries.studentEnrolled = false;
                    }
                    OKToEnroll = false;
                    OKToShow = false;
                }
                else {
                    enrollErrorTextArea.setText("Please choose a student and a class before you click the Enroll Student in Class Button.");
                }
                if (Queries.duplicateFlag){
                    enrollErrorTextArea.setText("That student is already enrolled in that class. To review a student's current schudule, go to the Student Features tab and use the Show Schedule option.");
                    Queries.duplicateFlag = false;
                }
            }
        });
        enrollStudentComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setClassComboBox();
                setStudentComboBox();
            }
        });
    }

    private void setStudentComboBox() {
        String start = "";
        enrollStudentComboBox.addItem(start);
        String studentInComboBox;
        String classInComboBox;
        try {
            ResultSet comboBoxRS = student.AllDataQuery();
            while (comboBoxRS.next()) {
                studentInComboBox = (comboBoxRS.getString(CreateTables.STUDENT_PK_COL) + " " + comboBoxRS.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + comboBoxRS.getString(CreateTables.STUDENT_LAST_COLUMN));
                enrollStudentComboBox.addItem(studentInComboBox);
            }
            comboBoxRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("Adding text to combo box in Student GUI " + sqle);
        }
    }

    private void setClassComboBox() {
        String start = "";
        classToEnrollComboBox.addItem(start);
        String studentInComboBox;
        String classInComboBox;
        try {
            MusicClass musicClass = new MusicClass();
            String enrollClassJoin = Queries.enrollJoin;
            ResultSet comboBoxRS = ConnectDB.statement.executeQuery(enrollClassJoin);
            while (comboBoxRS.next()) {
                studentInComboBox = (comboBoxRS.getString(CreateTables.CLASS_PK_COL) + " " + comboBoxRS.getString(CreateTables.CLASS_NAME_COLUMN) + " " + comboBoxRS.getString(CreateTables.CLASS_DAY_COLUMN)+ " " + comboBoxRS.getString(CreateTables.CLASS_TIME_COLUMN)+ " taught by " + comboBoxRS.getString(CreateTables.TEACHER_FIRST_COLUMN)+ " " + comboBoxRS.getString(CreateTables.TEACHER_LAST_COLUMN));
                classToEnrollComboBox.addItem(studentInComboBox);
            }
            comboBoxRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("Adding text to combo box in Student GUI " + sqle);
        }
    }

    public JPanel getPanel() {
        return EnrollGUI;
    }

    public void setEnrollStudentComboBox(JComboBox enrollStudentComboBox) {
        this.enrollStudentComboBox = enrollStudentComboBox;
    }
}
