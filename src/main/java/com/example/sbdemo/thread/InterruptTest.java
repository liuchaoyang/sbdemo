package com.example.sbdemo.thread;

public class InterruptTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("start...");
            for (int i = 0; i < 100000; i++) {
                System.out.println(i + 5000);
            }
            System.out.println("end...");
            System.out.println("interrupt error,isInterrupted:" + Thread.interrupted()); //清除中断状态
            System.out.println("interrupt error,isInterrupted:" + Thread.interrupted()); //清除中断状态
        });
        t.start();

        try {
            Thread.sleep(100L);
            t.interrupt(); //会清除停止状态
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("interrupt error,isAlive:" + t.isAlive());
//        System.out.println("interrupt error,isInterrupted:" + t.isInterrupted()); //不清除中断状态
//        System.out.println("interrupt error,isInterrupted:" + t.isInterrupted()); //不清除中断状态
    }
}
