package com.calc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {
    private final JFrame guiFrame = new JFrame();
    private final ArrayList<JButton> buttArr = new ArrayList<>();


    public Window() {
        guiFrame.setTitle("Calculator");
    }

    public void createGUI() {
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setLayout(new BorderLayout());
        guiFrame.setPreferredSize(new Dimension(400,700));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for(int i = 0; i < 20; i++) {
            String buttText = switch (i) {
                case 0 -> "B";
                case 1 -> "(";
                case 2 -> ")";
                case 3 -> "C";
                case 7 -> "/";
                case 11 -> "*";
                case 15 -> "-";
                case 16 -> "0";
                case 17 -> ".";
                case 18 -> "=";
                case 19 -> "+";
                default -> "" + i;
            };

            buttArr.add(new JButton(buttText));
            if(i == 9) {
                buttArr.get(i).setText("0");
            }
            buttArr.get(i).setPreferredSize(new Dimension(100,100));
            panel.add(buttArr.get(i));
        }

        guiFrame.add(panel, BorderLayout.CENTER);

        guiFrame.pack();
        guiFrame.setVisible(true);
    }

    private String shunt(String input) {
        OutputQueue queue = new OutputQueue();
        OperatorStack stack = new OperatorStack();

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '0') {
                queue.push(input.charAt(i));
            }
        }

        return "";
    }
}
