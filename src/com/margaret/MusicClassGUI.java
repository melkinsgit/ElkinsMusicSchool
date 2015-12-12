package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/11/2015.
 */
public class MusicClassGUI {
    private JPanel musicClassGUITab;
    private JButton quitButton;
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
    String start = "";
    Teacher teacher = new Teacher();

    public MusicClassGUI() {

        classErrorTextArea.setLineWrap(true);
        classErrorTextArea.setEditable(false);
        classResultsTextArea.setLineWrap(true);
        classResultsTextArea.setEditable(false);

        setStudentClassComboBox();
        setTimeComboBox();
        setDayComboBox();
        setTeacherComboBox();

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
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        timeHourComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        priceTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        classAllTeachersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        classResultsTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        classErrorTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
    }

    private void setTeacherComboBox() {
        classAllTeachersComboBox.addItem(start);
        String teachersInComboBox;
        try {
            ResultSet teachersComboBoxRS;
            teachersComboBoxRS = teacher.AllDataQuery();
            while (teachersComboBoxRS.next()){
                teachersInComboBox = (teachersComboBoxRS.getString(CreateTables.TEACHER_PK_COL) + " " + teachersComboBoxRS.getString(CreateTables.TEACHER_FIRST_COLUMN) + " " + teachersComboBoxRS.getString(CreateTables.TEACHER_LAST_COLUMN));
                classAllTeachersComboBox.addItem(teachersInComboBox);
            }
        }
        catch (SQLException sqle){
            System.out.println("In set teacher combo box " + sqle);
        }
    }

    private void setDayComboBox() {
        dayComboBox.addItem(start);
        String [] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < daysArr.length; i++){
            dayComboBox.addItem(daysArr[i]);
        }
    }

    private void setTimeComboBox() {
        timeHourComboBox.addItem(start);
        timeAMorPMComboBox.addItem(start);
        String [] hoursArr = {"1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00"};
        String [] amOrPMArr = {"am", "pm"};
        for (int i = 0; i < hoursArr.length; i++){
            timeHourComboBox.addItem(hoursArr[i]);
        }
        for (int i = 0; i < amOrPMArr.length; i++){
            timeAMorPMComboBox.addItem(amOrPMArr[i]);
        }
    }

    public void setStudentClassComboBox (){
        // put all student names in the combo box
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
