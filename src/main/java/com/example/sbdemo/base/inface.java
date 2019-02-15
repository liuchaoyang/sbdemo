package com.example.sbdemo.base;

public interface inface {

    default void test() {
        System.out.println("default...");
    }

    static void m() {
        System.out.println("m...");
    }
    String inDomain = "www.baidu.com";

    public abstract void getUrl();
}
