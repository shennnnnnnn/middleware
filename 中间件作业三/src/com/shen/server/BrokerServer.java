package com.shen.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class BrokerServer implements Runnable{

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("接受到数据：" + str);
                if ("CONSUME".equals(str)) {
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else {
                    Broker.produce(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }

    }
}
