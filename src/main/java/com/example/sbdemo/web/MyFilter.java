package com.example.sbdemo.web;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("myfilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myfilter doFilter...");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if (httpServletRequest.getRequestURI().contains("/301")) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("权限不足！");
            return;
        }
        chain.doFilter(request, response);
        System.out.println("myfilter doFilter...");
    }

    @Override
    public void destroy() {
        System.out.println("myfilter destroy...");
    }
}
