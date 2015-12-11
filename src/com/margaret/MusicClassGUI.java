package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/11/2015.
 */
public class MusicClassGUI {
    private JPanel musicClassGUITab;
    private JButton button1;
    private JButton enrollStudentInClassButton;
    private JTextField classNameTextField;
    private JTextField priceTextField;
    private JComboBox allStudentsComboBox;
    private JComboBox allClassesComboBox;
    private JButton addClassButton;
    private JTextArea classResultsTextArea;
    private JTextArea classErrorTextArea;
    private JLabel classNameLabel;
    private JComboBox dayComboBox;
    private JLabel classDayLabel;
    private JLabel classTimeLabel;
    private JComboBox timeHourComboBox;
    private JComboBox timeAMorPMComboBox;
    private JLabel classPriceLabel;
    private JComboBox classAllTeachersComboBox;
    private JLabel addClassInstrLabel;
    private JLabel enrollStudInstrLabel;

    boolean OKToShow = false;
    boolean OKToEnroll = false;
    String studentToSkedStr;
    String classToEnrollStr;
    Student student = new Student();

    public MusicClassGUI() {

        classErrorTextArea.setLineWrap(true);
        classErrorTextArea.setEditable(false);
        classResultsTextArea.setLineWrap(true);
        classResultsTextArea.setEditable(false);


        setStudentComboBox();

        allStudentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this is for use in Class GUI

                classResultsTextArea.setText("");
                Object studentToSked = allStudentsComboBox.getSelectedItem();
                studentToSkedStr = (String) studentToSked;
                Student student = new Student();
                if (!studentToSkedStr.equals("")){
                    OKToShow = true;
                }
                else {
                    classErrorTextArea.setText("That is not a valid choice. Please choose a student name from the drop down menu.");
                    classResultsTextArea.setText("");
                }
                if (OKToShow) {
                    ;
                }
            }
        });

        allClassesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classErrorTextArea.setText("");
                Object classToEnroll = allClassesComboBox.getSelectedItem();
                classToEnrollStr = (String) classToEnroll;
                MusicClass musicClass = new MusicClass();
                if (!classToEnrollStr.equals("")){
                    OKToEnroll = true;
                }
                else {
                    classErrorTextArea.setText("That is not a valid choice. Please choose a class from the drop down menu.");
                    classResultsTextArea.setText("");
                }
            }
        });


        enrollStudentInClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OKToShow && OKToEnroll){
                    MusicClass musicClass = new MusicClass();
                    System.out.println("Ready to enroll " + studentToSkedStr + " in " + classToEnrollStr);
                    musicClass.EnrollInClass(classToEnrollStr, studentToSkedStr);
                }
                else {
                    classErrorTextArea.setText("Please choose a student and a class before you click the Enroll Student in Class Button.");
                }
                if (Queries.duplicateFlag){
                    classErrorTextArea.setText("That student is already enrolled in that class. To review a student's current schudule, go to the Student Features tab and use the Show Schedule option.");
                }
            }
        });
    }

    public void setStudentComboBox (){
        // put all student names in the combo box
        String start = "";
        allStudentsComboBox.addItem(start);
        allClassesComboBox.addItem(start);
        String studentInComboBox;
        String classInComboBox;
        try {
            MusicClass musicClass = new MusicClass();
            ResultSet comboBoxRS = student.AllDataQuery();
            while (comboBoxRS.next()) {
                studentInComboBox = (comboBoxRS.getString(CreateTables.STUDENT_PK_COL) + " " + comboBoxRS.getString(CreateTables.STUDENT_FIRST_COLUMN) + " " + comboBoxRS.getString(CreateTables.STUDENT_LAST_COLUMN));
                allStudentsComboBox.addItem(studentInComboBox);
            }
            comboBoxRS.beforeFirst();

            ResultSet classComboBoxRS = MusicClass.AllDataQuery();
            while (classComboBoxRS.next()) {
                classInComboBox = ((classComboBoxRS.getString(CreateTables.CLASS_PK_COL)) + " " + (classComboBoxRS.getString(CreateTables.CLASS_NAME_COLUMN)) + " " + (classComboBoxRS.getString(CreateTables.CLASS_DAY_COLUMN)) + " " + (classComboBoxRS.getString(CreateTables.CLASS_TIME_COLUMN)));
                allClassesComboBox.addItem(classInComboBox);
            }
            classComboBoxRS.beforeFirst();
        }
        catch (SQLException sqle){
            System.out.println("Adding text to combo box in Student GUI " + sqle);
        }
    }




    public JPanel getPanel () {return musicClassGUITab;}
}
