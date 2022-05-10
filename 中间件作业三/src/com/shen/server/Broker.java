package com.shen.server;

import java.util.concurrent.ArrayBlockingQueue;


//消息处理中心类
public class Broker {
    private final static int MAX_SIZE = 5;
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    //生产消息
    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.println("成功发送消息：" + msg + ",当前已有的消息数量：" + messageQueue.size());
        } else {
            System.out.println("消息队列容量已满");
        }
        System.out.println("----------------------------");
    }

    //消费消息
    public static String consume() {
        String msg = messageQueue.poll();
        if (msg != null) {
            System.out.println("已经消费消息:" + msg + ",当前已有的消息数量是" + messageQueue.size());
        } else {
            System.out.println("消息队列消息为空");
        }

        System.out.println("----------------------------");
        return msg;
    }
}
