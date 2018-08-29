package com.example.sbdemo.user.mapper;

import com.example.sbdemo.user.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE mobile = #{mobile}")
    User find(String mobile);
}
