package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Margaret on 12/8/2015.
 */
public class TeacherGUI extends JPanel {

    private JPanel TeacherGUITab;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
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

    protected String studentFirstToAdd;
    protected String studentLastToAdd;
    protected String studentPhoneToAdd;

    private String textInputError;
    Student student = new Student();

    public TeacherGUI () {

        teachErrorTextArea.setLineWrap(true);
        teachErrorTextArea.setEditable(false);
        teachResultTextArea.setLineWrap(true);
        teachResultTextArea.setEditable(false);


    }

    public JPanel getPanel() { return TeacherGUITab;}

}
