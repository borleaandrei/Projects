package com.rockets.topics;

import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapImpl<K, V> {

    private Map<K, V> map = new HashMap<>();

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void replace(K key, V value) {
        map.replace(key, value);
    }

}
