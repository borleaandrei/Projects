package com.rockets.topics;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Topics {

    private class VerifyExpiredTopicMessages implements Runnable {

        private String topicName;

        public VerifyExpiredTopicMessages(String topicName) {
            this.topicName = topicName;
        }

        @Override
        public void run() {
            //TODO needs more synchronization verification with message publishing functionality
            BlockingQueue<TopicMessage> topicQueue = topics.get(topicName);
            LocalDateTime currentTime = LocalDateTime.now();

            List<TopicMessage> messageList = topicQueue.stream()
                .filter(message -> {
                    //System.out.println(message.getExpirationTime() + " " + currentTime + " " + message.getExpirationTime().isAfter(currentTime));
                    return message.getExpirationTime().isAfter(currentTime);
                })
                .collect(Collectors.toList());

            if (messageList.size() < topicQueue.size()) {
                BlockingQueue<TopicMessage> newTopicQueue = new LinkedBlockingQueue<>(messageList);
                topics.replace(topicName, newTopicQueue);
            }
        }
    }

    private final ConcurrentMap<String, BlockingQueue<TopicMessage>> topics = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public Topics() {
        topics.put("topic", new LinkedBlockingQueue<>());
        scheduledExecutorService.scheduleWithFixedDelay(new VerifyExpiredTopicMessages("topic"), 0, 1, TimeUnit.SECONDS);
    }

    public void publishMessage(TopicMessage message) {
        BlockingQueue<TopicMessage> topic = topics.get(message.getTopic());

        //if the topic does not exist already, then we create it
        if (topic == null)
            synchronized (this) {
                topic = topics.get(message.getTopic());
                if (topic == null) {
                    topic = new LinkedBlockingQueue<>();
                    topics.put(message.getTopic(), topic);
                    scheduledExecutorService.scheduleWithFixedDelay(new VerifyExpiredTopicMessages("topic"), 0, 1, TimeUnit.SECONDS);
                }
            }

        try {
            topic.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<TopicMessage> getMessages(String topicName, Integer numberOfTopicMessages) {
        BlockingQueue<TopicMessage> topic = topics.get(topicName);

        Object[] objTopicMessages = topic.toArray();
        List<TopicMessage> messages = Arrays.stream(objTopicMessages).map(m -> (TopicMessage) m).collect(Collectors.toList());

        if (numberOfTopicMessages == -1)
            return messages;
        else
            return messages.subList(messages.size() - numberOfTopicMessages, messages.size());
    }

    public void stopTopicsVeirifcation() {
        scheduledExecutorService.shutdown();
    }

}
