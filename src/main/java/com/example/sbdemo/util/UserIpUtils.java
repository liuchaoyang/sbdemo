package com.example.sbdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserIpUtils {
    private static Logger logger = LoggerFactory.getLogger(UserIpUtils.class);

    /**
     * 获取客户端真实ip
     */
    public static String getCustomerIP(HttpServletRequest request) {
        String userip = request.getHeader("forwarded"); //格式 Forwarded: for=192.0.2.43,for=[2001:db8:cafe::17],for=unknown
        if(userip != null && userip.length() != 0 && !"unknown".equalsIgnoreCase(userip)) {
            if (userip.indexOf(",") >= 1) //如果有多个ip，则取第一个ip
            {
                String userips[] = userip.split(",");
                for (int i = 0; i < userips.length; i++) {
                    if("for".equals(userips[i].split("=")[0]) && userips[i].split("=")[1].indexOf(".")>=1){//判断ip格式
                        userip = userips[i].split("=")[1];
                    }
                }
            }
            else
            {
                if("for".equals(userip.split("=")[0]) && userip.split("=")[1].indexOf(".")>=1){//判断ip格式
                    userip = userip.split("=")[1];
                } else {
                    userip = null;
                }
            }
        }
        //查询代理
        if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
            String userip_x = request.getHeader("x-forwarded-for");   //格式X-Forwarded-For: client1, proxy1, proxy2
            if(userip_x != null && userip_x.length() != 0 && !"unknown".equalsIgnoreCase(userip_x)) {
                if (userip_x.indexOf(",") >=1){//如果有多个
                    //暂时去掉下面的方式
                    //userip = request.getHeader("Client-IP");
                    if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
                        try{
                            userip = userip_x.split(",")[0];//取第一个
                        } catch (Exception e){
                            logger.info(e.getMessage(),e);
                        }
                    }
                } else {
                    userip = userip_x;
                }
            }
        }
        if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
            userip = request.getHeader("Client-IP");
        }
        if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
            userip = request.getHeader("Proxy-Client-IP");
        }
        if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
            userip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(userip == null || userip.length() == 0 || "unknown".equalsIgnoreCase(userip)) {
            userip = request.getRemoteAddr();
        }
        //如果代理IP是局域网IP
        if (("0.".equals(userip.substring(0, 2))) || ("10.".equals(userip.substring(0, 3))) || ("127.".equals(userip.substring(0, 4))) || ("192.".equals(userip.substring(0, 4))) || ("172.".equals(userip.substring(0, 4))))
        {
            userip = request.getRemoteAddr();
        }
        return userip;
    }
}
