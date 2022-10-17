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
        OutputQueue<String> outQueue = new OutputQueue<>();
        OperatorStack opStack = new OperatorStack();

        // todo: implement brackets

        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);

            Matcher num = Pattern.compile("\\d").matcher("" + curr);
            Matcher op = Pattern.compile("\\+|-|/|\\*|^").matcher("" + curr);

            if(num.matches()) {
                outQueue.push("" + curr);
            } else if(op.matches()) {
                outQueue.push(" ");
                // have to print number spaces here so multidigit numbers don't get break
                while(!opStack.isEmpty() && testPrecedence(curr, opStack.peek())) {
                    outQueue.push(opStack.pop() + " ");
                }
                opStack.push(curr);
            }
        }

        while(!opStack.isEmpty()) {
            outQueue.push(" " + opStack.pop());
        }

        return outQueue.dump();
    }

    private int operatorPrecedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    // return true if o1 has lesser precedence than o2
    private boolean testPrecedence(char o1, char o2) {
        return (operatorPrecedence(o1) < operatorPrecedence(o2));
    }
}
