package main;

import com.calc.Window;

public class Main {
    public static void main(String[] args) {
        // new Window.createGUI();

        Window w = new Window();
        System.out.println(w.shunt("3+4"));
        System.out.println(w.shunt("2*3+4"));
        System.out.println(w.shunt("30+52"));
        System.out.println(w.shunt("2^4"));
        System.out.println(w.shunt("1+2*3/4^5"));

    }
}
