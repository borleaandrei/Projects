package com.rockets.topics;

import com.rockets.Message;

import java.time.LocalDateTime;

public class TopicMessage extends Message {

    private LocalDateTime expirationTime;

    public TopicMessage(String h, String c, String t) {
        super(h, c, t);
        long nrSeconds = Long.parseLong(h);
        expirationTime = LocalDateTime.now().plusSeconds(nrSeconds);
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
}
