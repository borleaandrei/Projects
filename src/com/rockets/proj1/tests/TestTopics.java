package com.rockets.proj1.tests;

import com.rockets.proj1.topics.TopicMessage;
import com.rockets.proj1.topics.Topics;

public class TestTopics {

    public static void main(String[] args) {
        Topics topics = new Topics();

        Thread thread1 = new Thread(() -> {

            try {
                topics.publishMessage(new TopicMessage("7", "cotent1 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent2 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent3 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent4 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent5 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent6 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent7 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(500);
                topics.publishMessage(new TopicMessage("7", "cotent8 from " + Thread.currentThread(), "topic2"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {

            try {
                topics.publishMessage(new TopicMessage("10", "cotent11 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent22 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent33 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent44 from " + Thread.currentThread(), "topic1"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent55 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent66 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent77 from " + Thread.currentThread(), "topic2"));
                Thread.sleep(700);
                topics.publishMessage(new TopicMessage("10", "cotent88 from " + Thread.currentThread(), "topic2"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread showMessages = new Thread(() -> {
            int i = 0;

            try {

                while (i < 17) {
                    System.out.println(topics.getMessages("topic1", -1));
                    System.out.println(topics.getMessages("topic2", -1));
                    i++;

                    Thread.sleep(1000);
                }
                topics.stopTopicsVerifcation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        showMessages.start();

    }
}
