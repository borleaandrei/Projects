package com.rockets.proj1.tests;

import com.rockets.proj1.Broker;
import com.rockets.proj1.Message;

import java.util.Arrays;
import java.util.List;

public class QueueTest {

    // class for creating threads when receiving messages
    private static class Receiver implements Runnable {
        private Message message;
        private Broker broker;
        // private Thread th;

        public Receiver(Broker b, Message m) {
            message = m;
            broker = b;
            // th = new Thread();
        }

        public void run() {
            broker.receiveMessage(message);
            System.out.println("Received: " + message + " for queue: " + message.getHeader());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hey there, rockets!");
        List<Message> testMessages = Arrays.asList(
                new Message("program1", "message1"),
                new Message("program1", "message2"),
                new Message("program2", "message3"),
                new Message("program2", "message4"),
                new Message("program2", "message5"),
                new Message("program2", "message6"),
                new Message("program2", "message7"),
                new Message("program1", "message8"),
                new Message("program1", "message9"),
                new Message("program2", "message10"),
                new Message("program2", "message11"),
                new Message("program1", "message12"),
                new Message("program1", "message13")
        );

        Broker broker = new Broker();
        for (Message m : testMessages) {
            Receiver r = new Receiver(broker, m);
            new Thread(r).start();
        }

        broker.send("program2");
        broker.send("program2");

    }
}

