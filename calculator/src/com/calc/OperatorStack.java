package com.calc;

import java.util.ArrayList;

public class OperatorStack {
    private final ArrayList<Character> opStack = new ArrayList<>();

    public void push(char data) { opStack.add(0, data); }

    public char pop() { return opStack.remove(0); }
}
