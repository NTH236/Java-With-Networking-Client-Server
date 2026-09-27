package com.gpcoder.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastReceiver {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(MulticastSender.PORT)) {
            
            InetAddress group = InetAddress.getByName(MulticastSender.GROUP_ADDRESS);
            InetSocketAddress groupAddress = new InetSocketAddress(group, MulticastSender.PORT);
            NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            
            // chuẩn mới của Java 14+: joinGroup yêu cầu chỉ định rõ giao diện mạng
            socket.joinGroup(groupAddress, netIf);
            System.out.println("Đã gia nhập mạng lưới Multicast.");

            byte[] buffer = new byte[4096];
            while (true) {
                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(inPacket);
                
                String msg = new String(buffer, 0, inPacket.getLength());
                System.out.println("Tín hiệu thu được từ " + inPacket.getAddress() + ": " + msg);
            }
        } catch (Exception e) {
            System.err.println("Lỗi thiết bị thu: " + e.getMessage());
        }
    }
}