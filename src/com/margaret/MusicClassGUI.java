package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/11/2015.
 */
public class MusicClassGUI extends JFrame {
    private JPanel musicClassGUI;
    private JButton closeButton;
    private JTextField classNameTextField;
    private JTextField priceTextField;
    private JComboBox allStudentsComboBox;
    private JComboBox allClassesComboBox;
    private JButton addClassButton;
    private JTextArea classResultsTextArea;
    private JTextArea classErrorTextArea;
    private JComboBox timeHourComboBox;
    private JComboBox timeAMorPMComboBox;
    private JComboBox classAllTeachersComboBox;
    private JComboBox dayComboBox;

    private JLabel classDayLabel;
    private JLabel classTimeLabel;
    private JLabel classNameLabel;
    private JLabel classPriceLabel;
    private JLabel addClassInstrLabel;

    Student student = new Student();
    String start = "";
    Teacher teacher = new Teacher();
    String classToAddStr;
    String dayToAddStr;
    String timeToAddStr;
    String amOrpmToAddStr;
    String priceToAddStr;
    String teacherToAddStr;
    Integer teacherToAdd;
    boolean OKToAdd = false;
    String textInputError;

    public MusicClassGUI(){

        super("Add a Music Class");
        setPreferredSize(new Dimension(400, 300));
        setContentPane(musicClassGUI);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        classErrorTextArea.setLineWrap(true);
        classErrorTextArea.setEditable(false);
        classResultsTextArea.setLineWrap(true);
        classResultsTextArea.setEditable(false);

        setTimeComboBox();
        setDayComboBox();
        setTeacherComboBox();

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        dayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classErrorTextArea.setText("");
                classResultsTextArea.setText("");
            }
        });
        timeHourComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classErrorTextArea.setText("");
                classResultsTextArea.setText("");
            }
        });
        priceTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classErrorTextArea.setText("");
                classResultsTextArea.setText("");
            }
        });

        classAllTeachersComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                classErrorTextArea.setText("");
                classResultsTextArea.setText("");
            }
        });
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClass();
            }
        });
    }

    private void addClass() {

        MusicClass classToAdd = new MusicClass();
        classToAddStr = classNameTextField.getText();
        dayToAddStr = (String) dayComboBox.getSelectedItem();
        timeToAddStr = (String) timeHourComboBox.getSelectedItem();
        amOrpmToAddStr = (String) timeAMorPMComboBox.getSelectedItem();
        teacherToAddStr = (String) classAllTeachersComboBox.getSelectedItem();
        teacherToAdd = Integer.parseInt(teacherToAddStr.substring(0, teacherToAddStr.indexOf(" ")));
        priceToAddStr = priceTextField.getText();

        if (Queries.IsValidDBString(classToAddStr) && Queries.IsValidDBString(dayToAddStr) && Queries.IsValidDBString(timeToAddStr) && Queries.IsValidDBString(amOrpmToAddStr)) {
            OKToAdd = true;
        } else {
            textInputError = "You must enter a values in all fields. Please complete your entry and click Add This Student.";
            classErrorTextArea.setText(textInputError);
        }
        if (OKToAdd) {
            classToAdd.setClassName(classToAddStr);
            classToAdd.setClassDay(dayToAddStr);
            classToAdd.setClassTime(timeToAddStr + " " + amOrpmToAddStr);
            classToAdd.setClassPrice(priceToAddStr);
            classToAdd.setClassTeacher(teacherToAdd);
            classToAdd.AddClass();
            classResultsTextArea.setText(classToAddStr + " on " + dayToAddStr + " at " + timeToAddStr + " " + amOrpmToAddStr + " has been added.");
            OKToAdd = false;
        }
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
}
