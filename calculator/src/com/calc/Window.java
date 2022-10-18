package com.calc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {
    private final JFrame guiFrame = new JFrame();
    private final ArrayList<JButton> numArr = new ArrayList<>();
    private final ArrayList<JButton> mathArr = new ArrayList<>();
    private final ArrayList<JButton> calcArr = new ArrayList<>();

    public Window() {
        guiFrame.setTitle("Calculator");
    }

    public void createGUI() {
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setLayout(new BorderLayout());
        guiFrame.setPreferredSize(new Dimension(400,700));

        JPanel numPanel = new JPanel();
        numPanel.setLayout(new FlowLayout());

        JPanel mathPanel = new JPanel();
        mathPanel.setLayout(new GridLayout(0, 1));

        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new BoxLayout(calcPanel, BoxLayout.LINE_AXIS));

        for(int i = 0; i < 10; i++) {
            numArr.add(new JButton("" + i));
            numArr.get(i).setPreferredSize(new Dimension(100,100));
            numPanel.add(numArr.get(i));
        }

        for(int i = 0; i < 5; i++) {
            String text = switch(i) {
                case 0 -> "+";
                case 1 -> "-";
                case 2 -> "*";
                case 3 -> "/";
                case 4 -> "^";
                default -> "";
            };
            mathArr.add(new JButton(text));
            mathArr.get(i).setPreferredSize(new Dimension(100, 100));
            mathPanel.add(mathArr.get(i));
        }

        for(int i = 0; i < 4; i++) {
            String text = switch(i) {
                case 0 -> "C";
                case 1 -> "B";
                case 3 -> "=";
                default -> "";
            };
            calcArr.add(new JButton(text));
            calcArr.get(i).setPreferredSize(new Dimension(100, 100));
            calcArr.add(calcArr.get(i));
        }

        guiFrame.add(numPanel, BorderLayout.CENTER);
        guiFrame.add(mathPanel, BorderLayout.EAST);
        guiFrame.add(calcPanel, BorderLayout.NORTH);

        guiFrame.pack();
        guiFrame.setVisible(true);
    }



}
