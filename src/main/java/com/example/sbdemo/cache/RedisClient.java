package com.example.sbdemo.cache;

public interface RedisClient {
    boolean setnx(String key, String value);
}
