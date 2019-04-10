package com.example.sbdemo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args)  {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        int[] arr = {0,1,2,3,4,5,6,7,8,9};
        for (int a : arr) {
            new Thread(() -> {
                execute(a);
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End...............");
    }

    private static void execute(int index) {
        System.out.println("current thread start:" + index);
        try {
            Thread.sleep(1000L * index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current thread end:" + index);
    }

}
