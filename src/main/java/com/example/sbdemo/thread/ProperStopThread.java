package com.example.sbdemo.thread;

import java.time.LocalTime;

/**
 * https://www.cnblogs.com/ConstXiong/p/11684697.html
 *
 * 使用 volatile 修饰 stop 变量有必要吗？作用是什么？
 * 线程只能通过 runnable 状态到 terminated 状态，那线程状态是如何变化的呢？
 */
public class ProperStopThread {


    public static void main(String[] args) {

        RunningThread loop = new RunningThread();

        loop.start();
        try {
            loop.join(10);
            Thread.sleep(100);
            loop.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static class RunningThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(LocalTime.now() + "-开始loop..." + i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(LocalTime.now() + "-线程终止了");
                    break;
                }
                System.out.println(LocalTime.now() + "-结束loop..." + i);
            }
        }
    }

    public static class SleepThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(LocalTime.now() + "-开始loop..." + i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(LocalTime.now() + "-线程终止了");
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //如果没有下行代码，任务还会继续执行。
                    // 原因在于，sleep/join/wait线程被interrupt会抛出InterruptedException异常， 抛出异常会清除线程的中断状态，导致任务一直在执行
                    Thread.currentThread().interrupt();//重新设置线程为中断状态
                    System.out.println(LocalTime.now() + "-" + e);
                }
                System.out.println(LocalTime.now() + "-结束loop..." + i);
            }
        }
    }

    public static class StopVolatileThread extends Thread {

        private volatile boolean stop = false;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(LocalTime.now() + "-开始loop..." + i);
                if (stop) {
                    System.out.println(LocalTime.now() + "-线程终止了");
                    break;
                }
                try {
                    synchronized (this) {
                        Thread.currentThread().wait();
                    }

                } catch (InterruptedException e) {
                    System.out.println(LocalTime.now() + "-" + e);
                }
                System.out.println(LocalTime.now() + "-结束loop..." + i);
            }
        }

        @Override
        public void interrupt() {
            stop = true;
            super.interrupt();
        }
    }
}
