package com.example.sbdemo.thread;

public class Wait {

    private Object object = new Object();

    public void test() throws InterruptedException {
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (this) {
            object.wait();
        }
    }
}
