package com.rockets.topics;

import com.rockets.Message;

import java.time.LocalDateTime;

import static com.rockets.topics.Topics.MAXIMUM_MESSAGE_LIFETIME;

public class TopicMessage extends Message {

    private LocalDateTime expirationTime;

    public TopicMessage(String h, String c, String t) {
        super(h, c, t);
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(){
        long nrSeconds = Long.parseLong(getHeader());
        if(nrSeconds > MAXIMUM_MESSAGE_LIFETIME)
            nrSeconds = MAXIMUM_MESSAGE_LIFETIME;

        expirationTime = LocalDateTime.now().plusSeconds(nrSeconds);
    }
}
