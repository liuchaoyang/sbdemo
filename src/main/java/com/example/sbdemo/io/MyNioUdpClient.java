package com.example.sbdemo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * NIO 客户端，此处只有一个客户端连接
 */
public class MyNioUdpClient {
    public static void main(String[] args) throws Exception{
        DatagramChannel channel = DatagramChannel.open();
        ByteBuffer buffer = Charset.forName("utf-8").encode("this is Client.");
        channel.send(buffer, new InetSocketAddress("127.0.0.1", 8989));
    }
}
