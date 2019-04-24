package com.example.sbdemo.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsynService {


    @Async
    public void asyn() throws InterruptedException {
        System.out.println("asyn start..." + Thread.currentThread().getName());
        Thread.sleep(3000L);
        System.out.println("asyn end..." + Thread.currentThread().getName());
    }
}
