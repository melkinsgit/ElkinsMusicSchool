package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sn0173nd on 12/4/2015.
 */
public class FirstMenuGUI extends JFrame {  // add extends JFrame


    private JPanel rootPanel;
    private JButton ExitButton;
    private JLabel ExitButtonLabel;

        public FirstMenuGUI() {  // this is your constructor

            // add these lines of code, and they need to be first in your constructor
            super("HVAC Menu Options");
            setPreferredSize(new Dimension(400, 300));
            setContentPane(rootPanel);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);


            ExitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // code to execute when button pushed
                }
            });
        }
    }