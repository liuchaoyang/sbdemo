package com.example.sbdemo.base.timer;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Timer;
import java.util.TimerTask;

public class JavaTimer {
    Timer timer = new Timer();


    public static void main(String[] args) throws InterruptedException, Exception {
        JavaTimer app = new JavaTimer();
        app.singleThreadTest();

    }

    /**
     * 过去的时间点会立即自行
     */
    public void schedulePastTime() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2020, 10, 22), LocalTime.now());
        System.out.println(localDateTime.toString());
        java.util.Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));

        if (date.before(new java.util.Date())) {
            System.out.printf("" + Thread.currentThread().getName());
            System.out.println("这是一个过去的时间点");
        }
        timer.schedule(new MyTask(), date);
        timer.cancel();
    }

    /**
     * task抛出了RuntimeException，则timer再次执行schedule会报错：Timer already cancelled.
     * @throws InterruptedException
     */
    public void timerCancelledException() throws InterruptedException {
        timer.schedule(new MyErrorTask(null), 0);
        Thread.sleep(1000);
        timer.schedule(new MyTask(), 5000);
//        timer.scheduleAtFixedRate(timeTask, 3000, 1);
        System.out.println("end...");
        timer.cancel();
    }

    /**
     * timer是单线程执行，会造成延迟
     */
    public void singleThreadTest() {
        timer.schedule(new MyTask(), 0);
        timer.schedule(new MyTask(), 0);
        timer.schedule(new MyTask(), 0);
        System.out.println(Thread.currentThread().getName() + "-end...");
    }


    static class MyErrorTask extends TimerTask {

        private String a;
        MyErrorTask(String a) {
            this.a = a;
        }
        @Override
        public void run() {
            System.out.printf("" + Thread.currentThread().getName());
            System.out.println("MyErrorTask runing..." + a.equals("haha"));
        }
    }
    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.printf(Thread.currentThread().getName() + "-");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("timetask runing..." + LocalTime.now());
        }
    }

}
