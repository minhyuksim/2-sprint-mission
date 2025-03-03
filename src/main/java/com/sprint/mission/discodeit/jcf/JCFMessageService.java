package com.sprint.mission.discodeit.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.*;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();

    @Override
    public void Create(Message message) {
        data.put(message.getId(), message);
    }

    @Override
    public Message get(UUID id) {
        return data.get(id);
    }

    @Override
    public List<Message> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void update(Message message, String updatemessage) {
        message.updateMessage(updatemessage);
    }

    @Override
    public boolean delete(UUID id) {
        return data.remove(id) != null;
    }
}
