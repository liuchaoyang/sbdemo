package com.example.sbdemo.thread;

/**
 * 子线程访问到父线程的数据
 */
public class ChildThreadTest {
    private static ThreadLocal<String> threadLocal
//            = new ThreadLocal<String>();
            = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("fatherSet");
        Thread.currentThread().setName("father-thread");
        Thread child = new Thread(new ChildThread(), "son-thread");
        child.start();
        child.join();
        System.out.println("main thread :" + threadLocal.get());

    }

    static class ChildThread implements Runnable{

        String a = threadLocal.get();

        @Override
        public void run() {
            System.out.println("father threadLocal:" + a);
            System.out.println("runing get father threadLocal:" + threadLocal.get());
            threadLocal.set("son-set");

        }
    }
}
