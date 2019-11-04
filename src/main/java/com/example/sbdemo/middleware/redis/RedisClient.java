package com.example.sbdemo.middleware.redis;

public interface RedisClient {
    boolean setnx(String key, String value);
}
