package com.calc;

import java.util.ArrayList;

public class OutputQueue {
    private final ArrayList<Float> queue = new ArrayList<>();

    public void push(float data) { queue.add(data); }

    public float pop() { return queue.remove(0); }
}
