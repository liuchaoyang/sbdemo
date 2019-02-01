package com.example.sbdemo.base;

public interface inface {

    default void test() {
        System.out.println("default...");
    }

    String inDomain = "www.baidu.com";

    void getUrl();
}
