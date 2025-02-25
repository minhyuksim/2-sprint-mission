package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {

    void Create(Channel channel);

    Channel get(UUID id);

    List<Channel> getAll();

    void update(Channel channel, String updateChname);

    boolean delete(UUID id);
}
