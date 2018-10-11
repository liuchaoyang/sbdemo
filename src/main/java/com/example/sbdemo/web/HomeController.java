package com.example.sbdemo.web;

import com.example.sbdemo.common.ResultJson;
import com.example.sbdemo.exception.APIBaseException;
import com.example.sbdemo.user.entity.User;
import com.example.sbdemo.user.service.UserService;
import com.example.sbdemo.util.ExportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

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

    @RequestMapping("/test/log")
    public Object testLog() throws IOException, APIBaseException {
        LOGGER.error("-------error");
        LOGGER.warn("-------warn");
        LOGGER.info("-------info");
        LOGGER.debug("-------debug");
        LOGGER.trace("-------trace");

        User user = userService.findByMobile("13691156267");
        return ResultJson.success(user);

    }
}
