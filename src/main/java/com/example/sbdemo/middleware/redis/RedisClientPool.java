package com.example.sbdemo.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2018/10/29.
 */
@Component
public class RedisClientPool implements RedisClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean setnx(String key, String value) {
        ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
        Boolean isLock = opsForValue.setIfAbsent(key, value);
        if (isLock) {
            redisTemplate.expire(key,10, TimeUnit.MINUTES);
        }
        return isLock;
    }

}
