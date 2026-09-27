package com.gpcoder.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket()) {
            String message = "User đang kiểm tra UDP";
            byte[] data = message.getBytes();
            InetAddress server = InetAddress.getByName("127.0.0.1");

            DatagramPacket dp = new DatagramPacket(data, data.length, server, 8888);
            ds.send(dp);
            
            System.out.println("Đã chạy.");
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}