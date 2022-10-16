package com.calc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // made public to test, todo: make private
    public String shunt(String input) {
        OutputQueue<Character> queue = new OutputQueue<>();
        OutputQueue<Character> numQueue = new OutputQueue<>();
        OperatorStack<Character> stack = new OperatorStack<>();


        //input.replaceAll("\\s",""); //remove whitespace

        // only works for single digit ints, todo: refactor to allow for multi-digit ints / floats
        // todo: implement brackets
        for(int i = 0; i < input.length(); i++) {
            Matcher num = Pattern.compile("\\d").matcher("" + input.charAt(i));
            Matcher whitespace = Pattern.compile("\\s").matcher("" + input.charAt(i));

            if(num.matches() || input.charAt(i) == '.') {
                // character is numeric or a point
//                queue.push(input.charAt(i));
//
//                if (operator) {
//                    queue.push(stack.pop());
//                    operator = false;
//                }
                numQueue.push(input.charAt(i));
            } else if(whitespace.matches()) {
                while(!numQueue.isEmpty()) {
                    queue.push(numQueue.pop());
                }
            } else {
                //assuming valid input, todo: add case for invalid input
                stack.push(input.charAt(i));
            }
        }

        while(!numQueue.isEmpty()) {
            queue.push(numQueue.pop());
        }

        while(!stack.isEmpty()) {
            queue.push(stack.pop());
        }

        return queue.toString();
    }
}
