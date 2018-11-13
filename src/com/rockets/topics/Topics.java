package com.rockets.topics;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Topics {

    public static final long MAXIMUM_MESSAGE_LIFETIME = 60; //in seconds

    private class VerifyExpiredTopicMessages implements Runnable {

        private String topicName;

        public VerifyExpiredTopicMessages(String topicName) {
            this.topicName = topicName;
        }

        @Override
        public void run() {

            SynchronizedQueueImpl<TopicMessage> topicQueue = topics.get(topicName);
            LocalDateTime currentTime = LocalDateTime.now();

            List<TopicMessage> messageList = topicQueue.toArrayList().stream()
                .filter(message -> message.getExpirationTime().isAfter(currentTime))
                .collect(Collectors.toList());

            if (messageList.size() < topicQueue.size()) {
                SynchronizedQueueImpl<TopicMessage> newTopicQueue = new SynchronizedQueueImpl<>(new ArrayList<>(messageList));
                topics.replace(topicName, newTopicQueue);
            }
        }
    }

    private final SynchronizedMapImpl<String, SynchronizedQueueImpl<TopicMessage>> topics = new SynchronizedMapImpl<>();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public Topics() {
        topics.put("topic", new SynchronizedQueueImpl<>());
        scheduledExecutorService.scheduleWithFixedDelay(new VerifyExpiredTopicMessages("topic"), 0, 1, TimeUnit.SECONDS);
    }

    public void publishMessage(TopicMessage message) {
        SynchronizedQueueImpl<TopicMessage> topic = topics.get(message.getTopic());
        message.setExpirationTime(MAXIMUM_MESSAGE_LIFETIME);
        //if the topic does not exist already, then we create it
        if (topic == null)
            synchronized (this) {
                topic = topics.get(message.getTopic());
                if (topic == null) {
                    topic = new SynchronizedQueueImpl<>();
                    topics.put(message.getTopic(), topic);
                    scheduledExecutorService.scheduleWithFixedDelay(new VerifyExpiredTopicMessages(message.getTopic()), 0, 1, TimeUnit.SECONDS);
                }
            }

        topic.put(message);
    }

    public List<TopicMessage> getMessages(String topicName, Integer numberOfTopicMessages) {
        SynchronizedQueueImpl<TopicMessage> topic = topics.get(topicName);

        if (topic == null)
            return null;
        ArrayList<TopicMessage> messages = topic.toArrayList();

        if (numberOfTopicMessages == -1)
            return messages;
        else
            return messages.subList(messages.size() - numberOfTopicMessages, messages.size());
    }

    public void stopTopicsVerifcation() {
        scheduledExecutorService.shutdown();
    }


}
