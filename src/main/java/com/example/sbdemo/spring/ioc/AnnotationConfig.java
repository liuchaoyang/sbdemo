package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {

    @Bean
    public UserService myUserService() {
        return new SimpleServiceImpl();
    }


}
