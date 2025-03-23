package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.ChannelDTO;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel publiccreate(ChannelDTO.PublicChannelCreateDTO channelCreateDTO);
    Channel privatecreate(ChannelDTO.PrivateChannelCreateDTO channelCreateDTO);
    ChannelDTO.ChannelFindDTO find(UUID channelId);
    List<ChannelDTO.ChannelSummaryDTO> findAllChannelsByUserId(UUID userId);
    Channel update(UUID channelId, ChannelDTO.ChannelUpdateDTO channelUpdateDTO);
    void delete(UUID channelId);
}
