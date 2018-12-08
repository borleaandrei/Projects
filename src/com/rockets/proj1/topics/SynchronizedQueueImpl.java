package com.rockets.proj1.topics;

import java.util.ArrayList;

public class SynchronizedQueueImpl<E> {

    private ArrayList<E> queue;

    public SynchronizedQueueImpl() {
        queue = new ArrayList<>();
    }

    public SynchronizedQueueImpl(ArrayList<E> queue) {
        this.queue = queue;
    }

    public synchronized void put(E element) {
        queue.add(element);
    }

    public synchronized ArrayList<E> toArrayList() {
        return queue;
    }

    public synchronized int size() {
        return queue.size();
    }
}
