package com.example.sbdemo.user.service;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.mapper.UserMapper;
import com.example.sbdemo.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @ExceptionHandler
    public User findByMobile(String mobile) throws APIBaseException {
        if (!MobileUtils.isValid(mobile)) {
            throw APIBaseException.MOBILE_INVALID;
        }
        return userMapper.find(mobile);
    }
}
