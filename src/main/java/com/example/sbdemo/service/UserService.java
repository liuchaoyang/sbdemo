package com.example.sbdemo.service;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.model.User;

public interface UserService {
    User findByMobile(String mobile) throws APIBaseException;
    void saveUserToken();
}
