package com.shen.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class MqClient {

    public static void produce(String message) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(message);
        out.flush();
        out.close();
    }

    public static String consume() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("CONSUME");
        out.flush();
        String message = in.readLine();
        out.close();
        in.close();
        return message;
    }
}
