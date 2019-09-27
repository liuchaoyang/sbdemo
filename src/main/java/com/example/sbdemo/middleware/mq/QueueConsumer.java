package com.example.sbdemo.middleware.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class QueueConsumer extends ActiveMqUtils{

    public static void main(String[] args) throws JMSException, IOException {
        //2.获取连接
        Connection connection = new QueueConsumer().getConnection();
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
        //6.创建消息consumer
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("start MessageListener...");
                TextMessage receive = (TextMessage) message;
                String text = null;
                try {
                    text = receive.getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println("MessageListener:" + text);
                throw new RuntimeException("就是要報錯");
//                try {
//                    session.commit();
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
            }
        });

        //8.等待键盘输入
        System.in.read();

        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    private static void recieve(MessageConsumer consumer) throws JMSException {
        System.out.println("start receive...");
        TextMessage receive = (TextMessage) consumer.receive();
        String text = receive.getText();
        System.out.println("receive:" + text);
    }

}
