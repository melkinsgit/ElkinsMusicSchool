package com.margaret;

import javax.swing.*;

/**
 * Created by sn0173nd on 12/7/2015. This class is modeled off Clara's TabbedJFrame project
 */
public class MainGUI extends JFrame{

    private JPanel rootPanel;

    //Not created in GUI designer
    private JTabbedPane tabbedPane;


    //Note that this fails with a NullPointer if the default layoutmanager (GridLayoutManager) for this form is used
    //Since all it does is hold the JTabbedPane, set the layout manager to something (probably anything) else.

    public MainGUI() {
        setContentPane(rootPanel);

        //Create a a JTabbedPanel, add to JPanel, add tabs to JTabbedPane.
        tabbedPane = new JTabbedPane();
        rootPanel.add(tabbedPane);
        tabbedPane.add("Student Features", new StudentGUI().getPanel());
        tabbedPane.add("Teacher Features", new TeacherGUI().getPanel());
        tabbedPane.add("Music Class Features", new MusicClassGUI().getPanel());

        setVisible(true);
        pack();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
