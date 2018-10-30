package com.rockets.tests;

import com.rockets.topics.TopicMessage;
import com.rockets.topics.Topics;

public class Test {

    public static void main(String[] args) {
        Topics topics = new Topics();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    topics.publishMessage(new TopicMessage("7", "cotent1 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent2 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent3 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent4 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent5 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent6 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent7 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(1000);
                    topics.publishMessage(new TopicMessage("7", "cotent8 from "+Thread.currentThread(), "topic2"));
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
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    topics.publishMessage(new TopicMessage("7", "cotent1 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent2 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent3 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent4 from "+Thread.currentThread(), "topic1"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent5 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent6 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent7 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(700);
                    topics.publishMessage(new TopicMessage("7", "cotent8 from "+Thread.currentThread(), "topic2"));
                    Thread.sleep(700);


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
        });

        thread1.start();
        thread2.start();


    }
}
