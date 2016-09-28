package com.sliding;

public class Main {

    public static void main(String[] args) {
        System.out.println(args[0]);

        Menu menu = new Menu(args[0]);
        menu.play();
    }
}
