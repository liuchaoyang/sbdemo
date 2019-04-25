package com.example.sbdemo.schedule;

import com.example.sbdemo.cache.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OrderOverdueJob {

    @Autowired
    private RedisLock lock;

//    @Scheduled(cron = "0 0/1 * 20 4 ?")
    public void check() {
        if (!lock.tryLock("orderOverdueJob", TimeUnit.MINUTES)) {
            return;
        }
        System.out.println("OrderOverdueJob check");
    }
}
