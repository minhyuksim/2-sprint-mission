package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.*;

public interface MessageRepository {
    Message save(Message message);
    Message findById(UUID id);
    List<Message> findAll();
    void update(Message message);
    void delete(UUID id);
}
