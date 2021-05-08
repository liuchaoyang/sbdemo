package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlSpringConfig {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) applicationContext.getBean("com.example.sbdemo.spring.ioc.SimpleServiceImpl#0");
        userService.saveUserToken();
    }
}
