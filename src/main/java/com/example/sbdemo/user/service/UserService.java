package com.example.sbdemo.user.service;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.entity.User;

public interface UserService {
    User findByMobile(String mobile) throws APIBaseException;
    void saveUserToken(String token);
}
