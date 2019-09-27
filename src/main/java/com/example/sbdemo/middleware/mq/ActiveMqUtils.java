package com.example.sbdemo.middleware.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class ActiveMqUtils {

    protected Connection getConnection() throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        return connection;
    }
}
