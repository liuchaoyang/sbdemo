package com.example.sbdemo.thread;

import org.apache.commons.lang3.time.FastDateFormat;

public class MyThread {

    public static class MyRun implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println(i++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyTD extends Thread {
        @Override
        public void run() {
            int i = 0;
            long start = System.currentTimeMillis();
            while (i < 100000) {
                    System.out.println(i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("end..." + (end - start));
        }
    }
    public static void main(String[] args) throws InterruptedException {

        FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd hh-mm-ss");


//        MyTD myRun = new MyTD();
//        Thread thread = new Thread(myRun);
//
//        thread.setPriority(5);
//        thread.setDaemon(true);
//        thread.start();
//        synchronized (MyThread.class) {
//
//            thread.wait();
//        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        final ReentrantLock mainLock = new ReentrantLock();
//        Condition condition = mainLock.newCondition();
//        condition.signal();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println();
//            }
//        });
//        Future<?> future = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "liuchaoyang";
//            }
//        });
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        try {
//            InputStream in=System.in;
//            byte[] buf = new byte[1024];
//            in.read(buf);
//            System.out.println("end. " + new String(buf));
//
//            AtomicInteger atomicInteger = new AtomicInteger(123);
//            atomicInteger.addAndGet(1);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
