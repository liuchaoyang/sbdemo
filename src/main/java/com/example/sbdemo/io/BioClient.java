package com.example.sbdemo.io;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * BIO客户端
 */
public class BioClient {
    public static void main(String[] args) throws Exception{
        String data = "this is Client2.";
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("127.0.0.1"), 8989);
        socket.send(packet);
    }
}
