package com.rockets;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    private static int time = 150;       // max time to keep messages in queues

    private ArrayList<Queue> queues;         // list of queues created by the broker

    private boolean destinationConnected = false;


    public Broker() {
        this.queues = new ArrayList<Queue>();
    }

    // method that tells broker to receive messages
    public void receiveMessage(Message m) {
        // For every new message, adds it into appropriate queue or create a new one
        if (m.getTopic() == null) { // if non-topic message is received
            Queue q = searchByDestination(m.getHeader()); // search for queue
            q.add(m);
        }
    }

    // creates a new queue and adds it to the broker queues list
    private Queue createQueue(String destination) {
        Queue q = new Queue(destination);
        queues.add(q);
        return q;
    }

    // returns a queue that has destination, may be used in receiveMessage
    public synchronized Queue searchByDestination(String destination) {
        for(Queue q: queues) {
            if (q.getDestination().equals(destination)) {
                return q;
            }
        }
        // if no queue found, create a new queue with requested destination
        return createQueue(destination);
    }

    public void send(String program) {
        Queue q = searchByDestination(program);
        q.removeFirst();
    }
}
