package com.example.sbdemo.base;

import com.example.sbdemo.model.User;

public class DeliverTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("aaa");

        User user1 = newName(user);
        System.out.println(user1);

        Integer integer = new Integer("3");
        Integer integer1 = newInt(integer);
        System.out.println(integer);


    }

    static User newName(User user) {
        user.setUserName("bbb");
        return user;
    }

    static Integer newInt(Integer integer) {
        integer = 5;
        return integer;
    }
}
