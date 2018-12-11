package com.example.sbdemo.web;

import com.example.sbdemo.common.ResultJson;
import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.service.UserService;
import com.example.sbdemo.util.EnvUtils;
import com.example.sbdemo.util.ExportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EnvUtils envUtils;

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

    @RequestMapping("/test/log")
    public Object testLog() throws IOException, APIBaseException {
        User user = userService.findByMobile("13691156267");
        return ResultJson.success(user);
    }

    @RequestMapping("/test/active_profile")
    public Object activeProfile() throws IOException, APIBaseException {
        return ResultJson.success(envUtils.activeEnv());
    }

    @PostMapping("/test/post")
    public Object post(@RequestParam String orderIds,
                       @RequestParam int type) {
        System.out.println(orderIds);
        return ResultJson.success();
    }


    @GetMapping("/test/301")
    public Object test301(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://www.baidu.com");

        return ResultJson.success();
    }


}
