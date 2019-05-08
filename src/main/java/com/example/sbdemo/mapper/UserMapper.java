package com.example.sbdemo.mapper;

import com.example.sbdemo.model.User;

public interface UserMapper {

    int insert(User user);
    User find(String mobile);
}
