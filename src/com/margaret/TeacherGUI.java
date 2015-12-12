package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/8/2015.
 */
public class TeacherGUI extends JPanel {

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

    private JPanel StudentGUITab;
    private JButton addStudentButton;
    private JTextArea studResultsTextArea;
    private JTextArea studErrorTextArea;
    private JButton quitButton;
    private JLabel teachErrorLabel;
    private JLabel teachResultLabel;
    private JComboBox allTeachersComboBox;
    private JLabel showSkedLabel;
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

    protected String teacherFirstToAdd;
    protected String teacherLastToAdd;
    protected String teacherPhoneToAdd;

    private String textInputError;
    Student student = new Student();
    Teacher teacher = new Teacher();
    String start = "";

    public TeacherGUI () {

        teachErrorTextArea.setLineWrap(true);
        teachErrorTextArea.setEditable(false);
        teachResultTextArea.setLineWrap(true);
        teachResultTextArea.setEditable(false);

        setTeacherComboBox();
        allTeachersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        teacherFirstNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        teacherLastNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        teacherPhoneTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
//            studentToAdd.AddStudent(studentToAdd);
            teacherToAdd.AddTeacher();
            teachResultTextArea.setText(teacherFirstToAdd + " " + teacherLastToAdd + " has been added.");
            teacherFirstNameTextField.setText("");
            teacherLastNameTextField.setText("");
            teacherPhoneTextField.setText("");
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
