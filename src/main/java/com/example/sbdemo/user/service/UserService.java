package com.example.sbdemo.user.service;

import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByMobile(String mobile) {
        return userMapper.find(mobile);
    }
}
