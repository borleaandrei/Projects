package com.rockets;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    private static int time = 150;       // max time to keep messages in queues

    private ArrayList<Queue> queues;         // list of queues created by the broker
    // the broker should set this, queues shouldn't be aware of their maximum length
    private static int queueLength = 15;      // maximum number of messages to be put in queues
    private boolean receiving = false;        //
    private boolean destinationConnected = false;


    public Broker() {
        this.queues = new ArrayList<Queue>();
    }

    // method that tells broker to receive messages
    public synchronized void receiveMessage(Message m) {
        // wait for messages. For every new message, adds it into appropriate queue or create a new one
        while (receiving) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        receiving = true;

        if (m.getTopic() == null) { // if non-topic message is received
            Queue q = searchByDestination(m.getHeader()); // search for queue
            // TODO: semaphore by queueLength
            q.add(m);

        }
        //not receiving any more messages, notifying threads
        receiving = false;
        notifyAll();
    }

    // creates a new queue and adds it to the broker queues list
    private Queue createQueue(String destination) {
        Queue q = new Queue(destination);
        queues.add(q);
        return q;
    }

    // returns a queue that has destination, may be used in receiveMessage
    private Queue searchByDestination(String destination) {
        for(Queue q: queues) {
            if (q.getDestination().equals(destination)) {
                return q;
            }
        }
        // if no queue found, create a new queue with requested destination
        return createQueue(destination);
    }

    //TODO: some sending method
}
