package com.example.sbdemo.spring.ioc;

import com.example.sbdemo.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AnnotationConfigTest {

    @Bean
    public UserService myUserService() {
        return new SimpleServiceImpl();
    }

    @Pointcut("execution(* com.example.sbdemo.spring.ioc.SimpleServiceImpl.findByMobile(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void around(ProceedingJoinPoint pjp) {
        System.out.printf("around ahead.");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.printf("around last.");

    }




}
