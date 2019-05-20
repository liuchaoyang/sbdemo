package com.example.sbdemo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void execute() {
        System.out.println("Eexcute...");

        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        while (true) {
            logger.info("开始获取mail");
            String o = listOperations.rightPop("mail.queue", 30, TimeUnit.SECONDS);
            logger.info("获取到mail：{}", o);
        }
    }
}
