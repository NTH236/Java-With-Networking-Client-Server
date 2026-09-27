package com.gpcoder.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {
             
            System.out.println("Đã xâm nhập: " + socket);

            for (int i = '0'; i <= '9'; i++) {
                os.write(i);
                int ch = is.read();
                System.out.print((char) ch + " ");
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.err.println("Không thể thiết lập đường truyền.");
        }
    }
}