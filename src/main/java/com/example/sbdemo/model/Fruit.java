package com.example.sbdemo.model;

public class Fruit {

    private static Feiliao APPLY_FROM = new Feiliao(); //1，one time
    private Feiliao APPLY_FROM2 = new Feiliao(); //3
    private String color;

    public Fruit() {
        System.out.println("Fruit construct..."); //4
    }

    static {
        System.out.println("Fruit static..."); //2，one time
    }

    public static void grow() {
        System.out.println("Fruit static grow...");
    }

    protected static class Feiliao {

        public Feiliao() {
            System.out.println("Feiliao construct...");
        }

        public void supply() {
            System.out.println("Feiliao supply...");
        }
    }

    public void eat() {
        System.out.println("eat fruit...");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
