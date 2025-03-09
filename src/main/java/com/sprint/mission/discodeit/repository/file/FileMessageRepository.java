package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.List;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    @Override
    public Message save(Message message) {
        return null;
    }

    @Override
    public Message findById(UUID id) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return List.of();
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(UUID id) {

    }
}
