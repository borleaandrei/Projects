package com.rockets.tests;

import com.rockets.topics.TopicMessage;
import com.rockets.topics.Topics;

public class Test {

    public static void main(String[] args) {
        Topics topics = new Topics();

        try {
            topics.publishMessage(new TopicMessage("7", "cotent", "topic"));
            Thread.sleep(1000);
            topics.publishMessage(new TopicMessage("7", "cotent1", "topic"));
            Thread.sleep(1000);
            topics.publishMessage(new TopicMessage("7", "cotent2", "topic"));
            Thread.sleep(1000);
            topics.publishMessage(new TopicMessage("7", "cotent3", "topic"));
            Thread.sleep(1000);

            int i = 0;

            while (i < 15) {
                System.out.println(topics.getMessages("topic", -1).toString());
                i++;
                Thread.sleep(500);
            }

            topics.stopTopicsVeirifcation();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
