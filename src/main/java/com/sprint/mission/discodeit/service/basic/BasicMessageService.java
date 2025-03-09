package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {
    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        return null;
    }

    @Override
    public Message find(UUID messageId) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return List.of();
    }

    @Override
    public Message update(UUID messageId, String newContent) {
        return null;
    }

    @Override
    public void delete(UUID messageId) {

    }
}
