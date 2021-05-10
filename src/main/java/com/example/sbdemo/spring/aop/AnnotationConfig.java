package com.example.sbdemo.spring.aop;

import com.example.sbdemo.service.UserService;
import com.example.sbdemo.spring.ioc.SimpleServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AnnotationConfig {

    @Bean
    public UserService myUserService() {
        return new SimpleServiceImpl();
    }

    @Pointcut("execution(* com.example.sbdemo.spring.ioc.SimpleServiceImpl.findByMobile(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) {
        System.out.println("around ahead.");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around last.");

    }




}
