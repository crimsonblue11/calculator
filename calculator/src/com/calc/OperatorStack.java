package com.calc;

import java.util.ArrayList;

public class OperatorStack<T> {
    private final ArrayList<T> opStack = new ArrayList<>();

    public void push(T data) { opStack.add(0, data); }

    public T pop() { return opStack.remove(0); }

    public boolean isEmpty() { return opStack.isEmpty(); }
}
