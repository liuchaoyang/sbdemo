package com.example.sbdemo.thread;

import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private UserMapper userMapper;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void execute() {
        System.out.println("Eexcute...");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread() + "runable...");

                User user = new User();
                user.setUserName("message");
                user.setPassword(Thread.currentThread().getName());
                user.setMobile(String.valueOf(Thread.currentThread().getId()));
                int insert = userMapper.insert(user);
                if (insert>0) {
                    logger.info(Thread.currentThread() + "--insert:{}", user);
                }
            }
        };

        while (true) {
            executorService.execute(runnable);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
