package com.example.sbdemo.user.mapper;

import com.example.sbdemo.user.entity.User;

public interface UserMapper {

    int insert(User user);
    User find(String mobile);
}
