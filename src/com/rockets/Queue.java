package com.rockets;

import java.util.ArrayList;

public class Queue {
    private ArrayList<Message> messages;     // queue messages

    private String destination;             // destination from the header or topic if specified;
                                            // which message goes to which queue will be decided by the Broker,
                                            // using this field


    public Queue(String d) {
        this.messages = new ArrayList<Message>();
        this.destination = d;
    }

    // adds message to queue
    public void add(Message message) {
        messages.add(message);
    }
//
//    public void deleteMessage(int brokerTime) {
//        // TODO: delete message after time
//    }
//

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
}
