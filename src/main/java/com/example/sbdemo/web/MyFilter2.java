package com.example.sbdemo.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@Component
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter2 init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter2 doFilter...");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if (httpServletRequest.getRequestURI().contains("/301")) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("权限不足！");
            return;
        }
        chain.doFilter(request, response);
        System.out.println("MyFilter2 doFilter...");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter2 destroy...");
    }
}
