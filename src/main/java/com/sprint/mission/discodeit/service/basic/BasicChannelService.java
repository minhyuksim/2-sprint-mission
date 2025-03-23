package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.ChannelDTO;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;
    private final ReadStatusRepository readStatusRepository;
    private final MessageRepository messageRepository;


    private Instant getLatestMessageTimeByChannelId(UUID channelId) {
        List<Message> messages = messageRepository.findAllByChannelId(channelId);
        if (messages.isEmpty()) {
            return null;
        }
        return messages.stream()
                .map(Message::getCreatedAt)
                .max(Instant::compareTo)
                .orElse(null);
    }

    @Override
    public Channel publiccreate(ChannelDTO.PublicChannelCreateDTO channelCreateDTO) {
        Channel channel = Channel.builder()
                .type(ChannelType.PUBLIC)
                .name(channelCreateDTO.getName())
                .description(channelCreateDTO.getDescription())
                .build();
        return channelRepository.save(channel);
    }

    public Channel privatecreate(ChannelDTO.PrivateChannelCreateDTO channelCreateDTO) {
        Channel channel = Channel.builder()
                .type(ChannelType.PRIVATE)
                .name(null).build();
        Channel savedChannel = channelRepository.save(channel);

        channelCreateDTO.getUserIds().forEach(userId -> {
            ReadStatus readStatus = ReadStatus.builder()
                    .userId(userId)
                    .channelId(savedChannel.getId())
                    .lastReadAt(Instant.now())
                    .build();
            readStatusRepository.save(readStatus);
        });

        return savedChannel;
    }

    @Override
    public ChannelDTO.ChannelFindDTO find(UUID channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new RuntimeException("채널이 없음"));

        Instant latestMessageTime = getLatestMessageTimeByChannelId(channelId);

        List<UUID> participantUserIds = null;
        if (channel.getType() == ChannelType.PRIVATE) {
            participantUserIds = readStatusRepository.findAllByChannelId(channelId)
                    .stream()
                    .map(ReadStatus::getUserId)
                    .collect(Collectors.toList());
        }

        return ChannelDTO.ChannelFindDTO.builder()
                .id(channel.getId())
                .type(channel.getType())
                .name(channel.getName())
                .description(channel.getDescription())
                .lastMessageAt(latestMessageTime)
                .participantUserIds(participantUserIds)
                .build();
    }

    @Override
    public List<ChannelDTO.ChannelSummaryDTO> findAllChannelsByUserId(UUID userId) {

        List<Channel> publicChannels = channelRepository.findAll().stream()
                .filter(channel -> channel.getType() == ChannelType.PUBLIC)
                .collect(Collectors.toList());


        List<Channel> privateChannels = readStatusRepository.findAllByUserId(userId)
                .stream()
                .map(readStatus -> channelRepository.findById(readStatus.getChannelId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        List<Channel> allChannels = new ArrayList<>();
        allChannels.addAll(publicChannels);
        allChannels.addAll(privateChannels);


        return allChannels.stream()
                .map(channel -> {
                    Instant latestMessageTime = getLatestMessageTimeByChannelId(channel.getId());
                    return ChannelDTO.ChannelSummaryDTO.builder()
                            .id(channel.getId())
                            .type(channel.getType())
                            .name(channel.getName())
                            .description(channel.getDescription())
                            .latestMessageTime(latestMessageTime)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public Channel update(UUID channelId, ChannelDTO.ChannelUpdateDTO channelUpdateDTO) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new RuntimeException("채널이 없습니다."));

        // PRIVATE 채널은 수정 불가
        if (channel.getType() == ChannelType.PRIVATE) {
            throw new UnsupportedOperationException("PRIVATE 채널은 업데이트가 불가능 합니다.");
        }

        // PUBLIC 채널만 수정 가능
        channel.update(channelUpdateDTO.getName(), channelUpdateDTO.getDescription());
        return channelRepository.save(channel);
    }

    @Override
    public void delete(UUID channelId) {
        if (!channelRepository.existsById(channelId)) {
            throw new NoSuchElementException("Channel with id " + channelId + " not found");
        }
        channelRepository.deleteById(channelId);
    }
}
