package com.example.sbdemo.base.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {


    public static void main(String[] args) {

    }

    public static void arrayBlocking() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
        blockingQueue.add("a");
        try {
            blockingQueue.put("b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void linkedBlocking() {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(5);
        blockingQueue.add("a");
        try {
            blockingQueue.put("b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
