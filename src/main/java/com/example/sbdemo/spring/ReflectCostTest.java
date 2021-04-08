package com.example.sbdemo.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 反射的性能测试
 * 构造器性能差别是5倍
 */
public class ReflectCostTest {

    public static void main(String[] args) {
        long numTrials = (long) Math.pow(10, 7);

        long t1 = System.currentTimeMillis();
        for (int i=0; i<numTrials; i++) {
            new TestClass().toString();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("time: "+(t2-t1)+"ms");  //410ms

        for (int i=0; i<numTrials; i++) {
            new TestClass().toString2();
        }
        long t3 = System.currentTimeMillis();
        System.out.println("time: "+(t3-t2)+"ms");  //7647ms
    }

    static class TestClass implements Serializable {
        private String userName;
        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }

        public String toString2() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
