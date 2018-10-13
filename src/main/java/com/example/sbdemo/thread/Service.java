package com.example.sbdemo.thread;

public class Service {

    public synchronized static void methodA() {
        synchronized (Service.class) {
            System.out.println("start methodA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end methodA");
        }

    }

    public static void methodB(){
        synchronized (Service.class) {
            System.out.println("start methodB");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end methodB");
        }
    }

    public synchronized void methodC() {
        System.out.println("start methodC");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end methodC");
    }

    class Inner {
        private void methodD() {
            System.out.println("start methodD");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end methodD");

        }
    }
}
