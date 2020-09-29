package com.example.sbdemo.io;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务器端
 */
public class BioServer {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(8989);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while (true) {
            socket.receive(packet);    //服务器端在未收到数据时，会在此处被阻塞挂起
            System.out.println(new String(packet.getData()));
        }
    }
}
