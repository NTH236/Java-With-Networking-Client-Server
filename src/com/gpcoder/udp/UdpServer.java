package com.gpcoder.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static final int PORT = 8888;
    public static final byte[] BUFFER = new byte[4096];

    public static void main(String[] args) {
        try (DatagramSocket ds = new DatagramSocket(PORT)) {
            System.out.println("Trạm thu UDP mở tại cổng " + PORT);

            while (true) {
                DatagramPacket incoming = new DatagramPacket(BUFFER, BUFFER.length);
                ds.receive(incoming);

                String message = new String(incoming.getData(), 0, incoming.getLength());
                System.out.println("Thu thập được: " + message);

                // Dội bom ngược lại
                DatagramPacket outgoing = new DatagramPacket(
                        message.getBytes(), incoming.getLength(), 
                        incoming.getAddress(), incoming.getPort());
                ds.send(outgoing);
            }
        } catch (Exception e) {
            System.err.println("Trạm thu sụp đổ: " + e.getMessage());
        }
    }
}