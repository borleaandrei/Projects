package com.rockets;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    private static int time = 150;       // max time to keep messages in queues

    private ArrayList<Queue> queues;         // list of queues created by the broker


    public Broker() {
        this.queues = new ArrayList<Queue>();
    }

    // method that tells broker to receive messages
    public void receiveMessage(Message m) {
        // TODO: wait for messages. For every new message, adds it into appropriate queue or create a new one
    }

    // should be thread-safe
    private void createQueue(String destination) {
        // TODO: create appropriate queue
    }

    // returns a queue that has destination, may be used in receiveMessage
    private Queue searchByDestination(String destination) {
        // TODO: simple search algo
        return null;
    }

    //TODO: some sending method
}
