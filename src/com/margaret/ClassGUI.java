package com.margaret;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sn0173nd on 12/7/2015.
 */
public class ClassGUI extends JPanel {
    private JPanel ClassTabGUI;
    private JButton quitButton;
    private JLabel instructionsLabel;
    private JComboBox classComboBox;
    private JComboBox studentComboBox;
    private JLabel studentComboLabel;
    private JLabel classComboLabel;
    private JButton enrollButton;
    private JTextArea enrollResultTextArea;
    private JTextArea enrollErrorTextArea;
    private JLabel enrollResultsLabel;
    private JLabel enrollErrorLabel;

//    String start = "";
    String studentInComboBox;
    String classInComboBox;

    public ClassGUI () {
        System.out.println("In class gui constructor");
//        studentComboBox.addItem(start);
//        try {
//            Student student = new Student();
//            MusicClass musicClass = new MusicClass();
//            ResultSet comboBoxRS = student.AllDataQuery();
//            while (comboBoxRS.next()) {
//                studentInComboBox = (comboBoxRS.getString(CreateTables.STUDENT_FIRST_COLUMN)) + " " + (comboBoxRS.getString(CreateTables.STUDENT_LAST_COLUMN));
//                studentComboBox.addItem(studentInComboBox);
//            }
//            comboBoxRS.beforeFirst();
//
//            comboBoxRS = musicClass.AllDataQuery();
//            while (comboBoxRS.next()) {
//                classInComboBox = (comboBoxRS.getString(CreateTables.STUDENT_FIRST_COLUMN)) + " " + (comboBoxRS.getString(CreateTables.STUDENT_LAST_COLUMN));
//                classComboBox.addItem(classInComboBox);
//            }
//            comboBoxRS.beforeFirst();
//        }
//        catch (SQLException sqle){
//            System.out.println("Adding text to combo box in Student GUI " + sqle);
//        }
    }

    public JPanel getPanel() { return ClassTabGUI; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
