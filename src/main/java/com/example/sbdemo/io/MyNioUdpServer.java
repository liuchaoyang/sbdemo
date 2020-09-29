package com.example.sbdemo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * NIO 服务器端
 */
public class MyNioUdpServer {
    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();    //Selector选择器
        DatagramChannel channel = DatagramChannel.open();   //Channel通道
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress("127.0.0.1", 8989));
        channel.register(selector, SelectionKey.OP_READ);   //此通道注册在Selector时关注是否可读
        while (true) {
            selector.select();  //如果没有一个注册到此Selector上的通道就绪，则阻塞；反之，只要有一个通道就绪则不会被阻塞。selectNow方法不论是否有通道就绪，都不会阻塞。
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();   //选择就绪的通道
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {     //收到客户端数据
                    receive(key);
                }
                if (key.isWritable()) {     //服务器端通道准备好向客户端发送数据
                    send(key);
                }
            }
        }
    }

    /**
     * 服务器端收到客户端数据，并做处理
     * @param key
     */
    private static void receive(SelectionKey key) throws Exception{
        DatagramChannel channel = (DatagramChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.receive(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
    }
    /**
     * 服务器端通道已准备好向客户端发送数据
     * @param key
     */
    private static void send(SelectionKey key) {

    }
}
