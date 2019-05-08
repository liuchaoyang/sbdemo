package com.example.sbdemo;

import com.example.sbdemo.thread.MessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUpApplication implements ApplicationRunner {

    @Autowired
    MessageConsumer consumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        consumer.execute();
    }
}
