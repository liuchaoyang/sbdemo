package com.example.sbdemo.web;

import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.service.UserService;
import com.example.sbdemo.util.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {


    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "hello, man!";
    }

    @RequestMapping("/user/{mobile}")
    public Object findUser(@PathVariable String mobile, HttpServletRequest request) throws APIBaseException {
        return userService.findByMobile(mobile);
    }

    @RequestMapping("/user/export")
    public void export(HttpServletResponse response) throws IOException {
        ExportExcel.export(response);
    }
}
