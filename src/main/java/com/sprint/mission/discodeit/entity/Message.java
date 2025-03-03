package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message extends Common{
    private String message;
    private final UUID senderId;
    private final UUID channelId;


    public Message(String message, UUID senderId, UUID channelId) {
        super();
        this.message = message;
        this.senderId = senderId;
        this.channelId = channelId;
    }

    public String getMessage() {
        return message;
    }

    public void updateMessage(String message) {
        this.message = message;
        setUpdatedAt(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "[message : " + message + " 보내시는분은 : " + senderId + " 채널명은 : " + channelId + " 생성 시간 : " + getCreatedAt() + " 종료 시간 : " + getUpdatedAt() + "]";
    }
}
