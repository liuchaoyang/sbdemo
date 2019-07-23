package com.example.sbdemo.web;

import com.example.sbdemo.common.ResultJson;
import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.service.UserService;
import com.example.sbdemo.thread.AsynService;
import com.example.sbdemo.util.EnvUtils;
import com.example.sbdemo.util.ExportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private static String[] strs = null;

    @Autowired
    @Qualifier("userServiceImpl2")
    private UserService userService;
    @Autowired
    private EnvUtils envUtils;
    @Autowired
    private AsynService asynService;

    @RequestMapping("/index")
    public String index() throws InterruptedException {
        LOGGER.info("controller into..., Current thread:{}", Thread.currentThread().getName());
        Thread.sleep(3000L);
        LOGGER.info("controller end..., Current thread:{}", Thread.currentThread().getName());

        userService.saveUserToken();
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

    @RequestMapping("/test/active_profile")
    public Object activeProfile() throws IOException, APIBaseException {
        return ResultJson.success(envUtils.activeEnv());
    }


    @GetMapping("/test/301")
    public Object test301(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://www.baidu.com");

        return ResultJson.success();
    }

    @GetMapping("/test/{str}")
    public Object testString(@PathVariable String str) {
        System.out.println(str);

        return ResultJson.success(str);
    }

    @GetMapping("/test/asyn")
    public Object asyn() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + "start");
        asynService.asyn();
        return ResultJson.success();
    }

}
