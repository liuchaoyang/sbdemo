package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationSpringConfig {

    public static void main(String[] args) throws APIBaseException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext) applicationContext).scan("com.example.sbdemo.spring.ioc");
        ((AnnotationConfigApplicationContext) applicationContext).refresh();
        UserService userService = (UserService) applicationContext.getBean("myUserService");
        userService.saveUserToken();
        userService.findByMobile("1");
    }
}
