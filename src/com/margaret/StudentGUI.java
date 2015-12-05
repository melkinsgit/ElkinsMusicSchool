package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class StudentGUI extends JFrame implements WindowListener{
    private JPanel rootPanel;
    private JTextField enterFirstTextField;
    private JLabel enterFirstLabel;
    private JLabel enterLastLabel;
    private JTextField enterLastTextField;
    private JLabel enterPhoneLabel;
    private JTextField enterPhoneTextField;
    private JScrollPane studentScrollPane;
    private JTable studentTable;

    public StudentGUI (StudentTableModel studentTableModel){


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        setContentPane(rootPanel);
        pack();
        setTitle("Movie Database Application");
        //addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Set up JTable
        studentTable.setGridColor(Color.BLACK);
        studentTable.setModel(studentTableModel);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        // do something here?
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
