package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {

    void Create(Message message);

    Message get(UUID id);

    List<Message> getAll();

    void update(Message message, String updatemessage);

    boolean delete(UUID id);

}
