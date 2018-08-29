package com.example.sbdemo.web;

import com.example.sbdemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "hello, man!";
    }

    @RequestMapping("/user/{mobile}")
    public Object findUser(@PathVariable String mobile) {
        return userService.findByMobile(mobile);
    }
}
