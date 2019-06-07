package com.example.sbdemo.middleware.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        /*4.获取session  (参数1：是否启动事务,
          参数2：消息确认模式[
          AUTO_ACKNOWLEDGE = 1    自动确认
          CLIENT_ACKNOWLEDGE = 2    客户端手动确认
          DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
          SESSION_TRANSACTED = 0    事务提交并确认
         ])*/
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue = session.createQueue("test-queue");
        //6.创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //7.创建消息
        TextMessage textMessage = session.createTextMessage("欢迎来到MQ世界");
        //8.发送消息
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.send(textMessage);

        //提交事务
        session.commit();
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }

}
