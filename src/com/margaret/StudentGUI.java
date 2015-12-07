package com.margaret;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        allStudentsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

//        if (firstNameOK() && lastNameOK() && phoneOK())

        studentPhoneTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPhoneToAdd = studentPhoneTextField.getText();
            }
        });
    }

    public JPanel getPanel() { return StudentGUITab;}
}
