package com.example.sbdemo.service.impl;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.model.School;
import com.example.sbdemo.model.User;
import com.example.sbdemo.mapper.UserMapper;
import com.example.sbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    int count = 1;

    @PostConstruct
    public void init() {
        count++;
        System.out.println("UserServiceImpl2 PostConstruct:" + count);
    }


    @Override
    public User findByMobile(String mobile) throws APIBaseException {
        System.out.println("UserServiceImpl2 findByMobile");
        User user = new User();
        user.setUserId(111);user.setMobile("13691156267");user.setUserName("xxx");user.setPassword("111");
        user.setSchool(new School("学校", "地址"));
        userMapper.insert(user);

        return userMapper.find(mobile);
    }

    @Override
    public void saveUserToken() {
        System.out.println("UserServiceImpl2 saveUserToken");
    }

    @PreDestroy
    public void destroy() {
        count++;
        System.out.println("UserServiceImpl2 destroy:" + count);
    }
}
