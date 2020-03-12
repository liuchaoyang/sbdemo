package com.example.sbdemo.thread;

public class VolatileVisibleTest {
    int a = 1;
    int b = 2;

    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
//        if (a==1 && b == 3) {
//            //线程切换回导致这种情况，无法避免
//            System.out.println("a="+a+";b="+b);
//        }
        if (b == 3 && a==1) {
            System.out.println("b="+b+";a="+a);
        }
        //三种情况
        //b=3;a=3
        //b=2;a=3
        //b=2;a=1

        //非volatile才会出现的情况
        //b=3;a=1
    }

    //Thread.sleep()方法，目的是为了增加并发问题的产生几率，无其他作用
    public static void main(String[] args) {
        while (true){
            final VolatileVisibleTest test = new VolatileVisibleTest();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }
    }
}


