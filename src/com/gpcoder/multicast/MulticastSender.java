package com.gpcoder.multicast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastSender {
    public static final String GROUP_ADDRESS = "224.0.0.1";
    public static final int PORT = 9999;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(GROUP_ADDRESS);
            int counter = 0;

            while (true) {
                String msg = "Tín hiệu từ tổng hành dinh số " + counter++;
                DatagramPacket outPacket = new DatagramPacket(msg.getBytes(), msg.length(), address, PORT);
                
                socket.send(outPacket);
                System.out.println("Đã phát sóng: " + msg);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Lỗi trạm phát: " + e.getMessage());
        }
    }
}