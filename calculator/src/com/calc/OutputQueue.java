package com.calc;

import java.util.ArrayList;

public class OutputQueue<T> {
    private final ArrayList<T> queue = new ArrayList<>();

    public void push(T data) { queue.add(data); }

    public T pop() { return queue.remove(0); }

    public boolean isEmpty() { return queue.isEmpty(); }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < queue.size(); i++) {
            out += queue.get(i) + " ";
        }

        return out;
    }

    public int length() { return queue.size(); }
}
