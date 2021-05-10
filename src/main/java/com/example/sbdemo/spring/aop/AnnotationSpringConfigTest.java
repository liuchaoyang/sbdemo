package com.example.sbdemo.spring.aop;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationSpringConfigTest {

    public static void main(String[] args) throws APIBaseException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext) applicationContext).scan("com.example.sbdemo.spring.aop");
        ((AnnotationConfigApplicationContext) applicationContext).refresh();
        UserService userService = (UserService) applicationContext.getBean("myUserService");
        userService.saveUserToken();
        userService.findByMobile("1");
    }
}
