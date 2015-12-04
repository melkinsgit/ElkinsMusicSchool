package com.margaret;

import javax.swing.*;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class StudentGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField enterFirstTextField;
    private JLabel enterFirstLabel;
    private JLabel enterLastLabel;
    private JTextField enterLastTextField;
    private JLabel enterPhoneLabel;
    private JTextField enterPhoneTextField;
    private JScrollPane studentScrollPane;
    private JTable studentTable;

    private void createUIComponents() {
        // TODO: place custom component creation code here

        setContentPane(rootPanel);
        pack();
        setTitle("Movie Database Application");
        //addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
