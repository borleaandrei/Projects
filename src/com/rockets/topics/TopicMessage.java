package com.rockets.topics;

import com.rockets.Message;

import java.time.LocalDateTime;

public class TopicMessage extends Message {

    private LocalDateTime expirationTime;

    public TopicMessage(String h, String c, String t) {
        super(h, c, t);
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long maxMessageLifeTime){
        long nrSeconds = Long.parseLong(getHeader());
        if(nrSeconds > maxMessageLifeTime)
            nrSeconds = maxMessageLifeTime;

        expirationTime = LocalDateTime.now().plusSeconds(nrSeconds);
    }
}
