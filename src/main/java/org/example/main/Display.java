package org.example.main;

import javax.swing.*;

public class Display {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setFocusable(true);

        frame.setResizable(true);
        frame.setUndecorated(true);

        MyMain game = new MyMain(frame);

        frame.add(game);

        frame.setVisible(true);

    }
}
