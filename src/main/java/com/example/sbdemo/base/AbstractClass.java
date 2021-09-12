package com.example.sbdemo.base;

public abstract class AbstractClass {

    public void m1() {
        System.out.println("m1...");
    }

    public static void main(String[] args) {
        //AbstractClass不能直接instantiated
        AbstractClass abstractClass = new AbstractClass() {
            @Override
            public void m1() {
                super.m1();
            }
        };

        abstractClass.m1();
    }
}
