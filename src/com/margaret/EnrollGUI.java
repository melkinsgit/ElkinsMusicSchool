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
    private JComboBox classToEnrollComboBox;
    private JTextArea enrollResultsTextArea;
    private JTextArea enrollErrorTextArea;

    private JLabel enrollInstrLabel;
    private JLabel studToEnrollLabel;
    private JLabel enrollClassLabel;
    private JTextField enrollInstructTextField;

    Student student = new Student();
    String studentToEnrollStr;
    String classToEnrollStr;
    MusicClass musicClass = new MusicClass();

    public EnrollGUI() {



        enrollErrorTextArea.setLineWrap(true);
        enrollErrorTextArea.setEditable(false);
        enrollResultsTextArea.setLineWrap(true);
        enrollResultsTextArea.setEditable(false);

        setClassComboBox();
        setStudentComboBox();

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        enrollStudentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentToEnrollStr = (String) enrollStudentComboBox.getSelectedItem();
                enrollErrorTextArea.setText("");
                enrollResultsTextArea.setText("");
            }
        });

        classToEnrollComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classToEnrollStr = (String) classToEnrollComboBox.getSelectedItem();
                enrollErrorTextArea.setText("");
                enrollResultsTextArea.setText("");
            }
        });

        enrollStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               enrollStudent();
            }
        });

        enrollStudentComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });

        classToEnrollComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });

        classToEnrollComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollErrorTextArea.setText("");
                enrollResultsTextArea.setText("");
            }
        });
    }

    private void enrollStudent() {

        musicClass.EnrollInClass(classToEnrollStr, studentToEnrollStr);
        if (Queries.studentEnrolled) {
            enrollResultsTextArea.setText(studentToEnrollStr.substring(classToEnrollStr.indexOf(" ")) + " has been enrolled in " + classToEnrollStr.substring(classToEnrollStr.indexOf(" ")) + ".");
            Queries.studentEnrolled = false;
        }
        if (Queries.duplicateFlag){
            enrollErrorTextArea.setText("That student is already enrolled in that class. To review a student's current schudule, go to the Student Features tab and use the Show Schedule option.");
            Queries.duplicateFlag = false;
        }
    }

    private void setStudentComboBox() {
        String start = "";
        enrollStudentComboBox.addItem(start);
        String studentInComboBox;
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
        String classInComboBox;
        try {
            String enrollClassJoin = Queries.enrollJoin;
            ResultSet comboBoxRS = ConnectDB.statement.executeQuery(enrollClassJoin);
            while (comboBoxRS.next()) {
                classInComboBox = (comboBoxRS.getString(CreateTables.CLASS_PK_COL) + " " + comboBoxRS.getString(CreateTables.CLASS_NAME_COLUMN) + " " + comboBoxRS.getString(CreateTables.CLASS_DAY_COLUMN)+ " " + comboBoxRS.getString(CreateTables.CLASS_TIME_COLUMN)+ " taught by " + comboBoxRS.getString(CreateTables.TEACHER_FIRST_COLUMN)+ " " + comboBoxRS.getString(CreateTables.TEACHER_LAST_COLUMN));
                classToEnrollComboBox.addItem(classInComboBox);
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
}
