package com.calc;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame guiFrame = new JFrame();
    private JLabel title = new JLabel("Hello, world!");

    public Window() {
        guiFrame.setTitle("Calculator");
    }

    public void createGUI() {
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setLayout(new BorderLayout());
        guiFrame.setPreferredSize(new Dimension(400,800));

        JPanel panel = new JPanel();
        panel.add(title);

        guiFrame.add(panel, BorderLayout.CENTER);

        guiFrame.pack();
        guiFrame.setVisible(true);
    }

}
