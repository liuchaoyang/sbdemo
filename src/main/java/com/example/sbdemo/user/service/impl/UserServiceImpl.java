package com.example.sbdemo.user.service.impl;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.mapper.UserMapper;
import com.example.sbdemo.user.service.UserService;
import com.example.sbdemo.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public User findByMobile(String mobile) throws APIBaseException {
        if (!MobileUtils.isValid(mobile)) {
            throw APIBaseException.MOBILE_INVALID;
        }
        return userMapper.find(mobile);
    }

    @Override
    public void saveUserToken() {
        String key = "sanxi-user-token-13691156267";

        User user = new User();
        user.setMobile("13691156267");
        user.setUserId(8888888);
        Boolean setIfAbsent = redisTemplate.boundValueOps(key).setIfAbsent(user);
        System.out.println(setIfAbsent);

        Boolean hasKey = redisTemplate.hasKey(key);
        User redisUser = (User) redisTemplate.boundValueOps(key).get();
        System.out.println(redisUser);

        redisTemplate.delete(key);
        hasKey = redisTemplate.hasKey(key);
        System.out.println(hasKey);
    }
}
