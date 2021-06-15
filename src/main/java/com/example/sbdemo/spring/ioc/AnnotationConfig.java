package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.service.UserService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {

    @Bean
    public UserService myUserService() {
        return new SimpleServiceImpl();
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return configurableListableBeanFactory -> System.out.println(configurableListableBeanFactory.getClass().getName()
                + "@" + configurableListableBeanFactory.hashCode()
                + " postProcessBeanFactory ------------------");
    }

    @Bean
    public ApplicationContextAware applicationContextAware1() {
        return applicationContext -> System.out.println(applicationContext.getClass().getName()
                + "@" + applicationContext.hashCode()
                + " applicationContextAware1-------------------");
    }

    @Bean
    public ApplicationListener applicationListener1() {
        return event -> System.out.println(event.getClass().getName()
                + "@" + event.hashCode()
                + " applicationListener1------------------------");
    }


}
