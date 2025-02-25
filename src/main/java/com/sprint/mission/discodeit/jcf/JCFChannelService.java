package com.sprint.mission.discodeit.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;

public class JCFChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();

    @Override
    public void Create(Channel channel) {
        data.put(channel.getId(), channel);
    }

    @Override
    public Channel get(UUID id) {
        return data.get(id);
    }

    @Override
    public List<Channel> getAll() {
        return new ArrayList<>(data.values());
    }

    public void update(Channel channel, String updateChname) {
        channel.updateChname(updateChname);
    }

    public boolean delete(UUID id) {
        return data.remove(id) != null;
    }


}
