package com.gpcoder.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpMultiServer {
    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Mở cổng TCP " + SERVER_PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Phát hiện kết nối từ: " + clientSocket.getInetAddress());

                // Sử dụng Virtual Thread thay cho luồng vật lý nặng nề
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (Exception e) {
            System.err.println("Lỗi phòng thủ: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try (socket; 
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {
             
            int ch;
            while ((ch = is.read()) != -1) {
                os.write(ch); // Dội ngược tín hiệu về (Echo)
            }
        } catch (Exception e) {
            System.err.println("Mất dấu Client: " + e.getMessage());
        }
    }
}