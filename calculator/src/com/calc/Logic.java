package com.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    // made public to test, todo: make private
    public static float evaluate(String input) {
        OutputQueue<String> outQueue = new OutputQueue<String>();
        OperatorStack opStack = new OperatorStack();

        // todo: implement brackets

        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);

            Matcher num = Pattern.compile("\\d").matcher("" + curr);
            Matcher op = Pattern.compile("[+\\-/*^]").matcher("" + curr);

            if(num.matches() || curr == '.') {
                outQueue.push("" + curr);
            } else if(op.matches()) {
                outQueue.push(" ");
                // have to print number spaces here so multi digit numbers don't get break
                while(!opStack.isEmpty() && (operatorPrecedence(curr) < operatorPrecedence(opStack.peek())
                        || (operatorPrecedence(curr) == operatorPrecedence(opStack.peek()) && isLeftAssoc(curr)))) {
                    outQueue.push(opStack.pop() + " ");
                }
                opStack.push(curr);
            } else if (curr == '(') {
                opStack.push(curr);
            } else if(curr == ')') {
                while(opStack.peek() != '(') {
                    outQueue.push(" " + opStack.pop());
                }
                opStack.pop(); //discard left parenthesis
            }
        }

        while(!opStack.isEmpty()) {
            outQueue.push(" " + opStack.pop());
        }

//        System.out.println(outQueue.dump());

        return convertString(outQueue);
    }

    private static int operatorPrecedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    private static boolean isLeftAssoc(char op) {
        return (op == '+' || op == '-' || op == '*' || op == '/');
    }

    private static float convertString(OutputQueue<String> in) {
        String[] array = in.dump().split(" ");
        float op1 = Float.parseFloat(array[0]);
        float op2 = Float.parseFloat(array[1]);
        char operator = array[2].charAt(0);

        return actualEval(op1, op2, operator);
    }

    private static float actualEval(float op1, float op2, char operand) {
        float out;
        switch(operand) {
            case '+' -> out = op1 + op2;
            case '-' -> out = op1 - op2;
            case '/' -> out = op1 / op2;
            case '*' -> out = op1 * op2;
            case '^' -> out = (float) Math.pow(op1, op2);
            default -> out = 0;
        }

        return out;
    }
}
