package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.model.User;
import com.example.sbdemo.service.UserService;
import org.springframework.stereotype.Service;

public class SimpleServiceImpl implements UserService {

    @Override
    public User findByMobile(String mobile) throws APIBaseException {
        System.out.println("findByMobile");
        return null;
    }

    @Override
    public void saveUserToken() {
        System.out.println("saveUserToken");

    }
}
