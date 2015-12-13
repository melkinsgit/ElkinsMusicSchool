package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextArea textArea1;
    private JTextField toEnrollAStudentTextField;
    private JTextArea textArea2;
    Student student = new Student();

    public EnrollGUI() {

        setClassComboBox();
        setStudentComboBox();

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
    }

    private void setStudentComboBox() {
        String start = "";
        enrollStudentComboBox.addItem(start);
        String studentInComboBox;
        String classInComboBox;
        try {
//            MusicClass musicClass = new MusicClass();
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

}
