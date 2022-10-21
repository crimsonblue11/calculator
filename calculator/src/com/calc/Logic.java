package com.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    // made public to test, todo: make private
    public static String shunt(String input) {
        OutputQueue<String> outQueue = new OutputQueue<>();
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

        return outQueue.dump();
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

    private static float evaluate(String in) {

        return 0f;
    }
}
