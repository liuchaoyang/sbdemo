package com.example.sbdemo.model;

public class Apple extends Fruit {

    private static Feiliao APPLY_FROM = new Feiliao();

    public Apple() {
        System.out.println("Apple construct...");
    }

    static {
        System.out.println("Apple static...");
    }

    public static void grow() {
        System.out.println("Apple static grow...");
    }

    public void eat() {
        System.out.println("eat apple...");
    }

    public void cirle() {
        System.out.println("cirle apple...");
    }
}
