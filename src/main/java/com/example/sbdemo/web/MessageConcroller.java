package com.example.sbdemo.web;

import com.example.sbdemo.common.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MessageConcroller {
    private static final Logger logger = LoggerFactory.getLogger(MessageConcroller.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final AtomicInteger mailNum = new AtomicInteger(0);

    @RequestMapping("/send/mail")
    public ResultJson sendMail() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("mail.queue", String.valueOf(mailNum.getAndIncrement()));
        logger.info("发送mail：{}", mailNum.get());
        return ResultJson.success();
    }
}
