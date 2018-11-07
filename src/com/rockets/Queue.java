package com.rockets;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Message> messages;     // queue messages

    private String destination;             // destination from the header or topic if specified;
                                            // which message goes to which queue will be decided by the Broker,
                                            // using this field
    private static int MAX_QUEUE_LENGTH = 5; // maximum number of messages to be put in queues
    private boolean receiving;


    public Queue(String d) {
        this.messages = new ArrayList<Message>();
        this.destination = d;
        this.receiving = false;
    }

    // adds message to queue
    public synchronized void add(Message message) {
        while (receiving) {
            try {

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        receiving = true;
        if (getLength() >= MAX_QUEUE_LENGTH) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        messages.add(message);
        receiving = false;
        //not receiving any more messages, notifying threads
        notifyAll();
    }

    public String toString() {
        String ret = "";
        for (Message m : messages) {
            ret += m.toString();
        }
        return ret;
    }

    public String getDestination() {
        return this.destination;
    }

    public synchronized void removeFirst() {
        while(getLength() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.messages.remove(0);
        notifyAll();
    }

    public int getLength() {
        return messages.size();
    }
}
