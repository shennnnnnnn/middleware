package com.shen.client;

import java.io.IOException;

public class ConsumeClient {
    public static void main(String[] args) throws IOException {
        String message = MqClient.consume();
        System.out.println("消费的消息是 : " + message);
    }
}
