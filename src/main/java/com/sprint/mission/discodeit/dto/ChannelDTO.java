package com.sprint.mission.discodeit.dto;

import com.sprint.mission.discodeit.entity.ChannelType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class ChannelDTO {

    @Getter
    public static class PublicChannelCreateDTO {
        private String name;
        private String description;

        @Builder
        public PublicChannelCreateDTO(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    @Getter
    public static class PrivateChannelCreateDTO {
        private List<UUID> userIds;
        
        @Builder
        public PrivateChannelCreateDTO(List<UUID> userIds) {
            this.userIds = userIds;
        }
    }

    @Getter
    public static class ChannelFindDTO {
        private UUID id;
        private ChannelType type;
        private String name;
        private String description;
        private Instant lastMessageAt;
        private List<UUID> participantUserIds;

        @Builder
        public ChannelFindDTO(UUID id,ChannelType type,String name, String description, Instant lastMessageAt, List<UUID> participantUserIds) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.description = description;
            this.lastMessageAt = lastMessageAt;
            this.participantUserIds = participantUserIds;
        }
    }

    @Getter
    public static class ChannelSummaryDTO {
        private UUID id;
        private ChannelType type;
        private String name;
        private String description;
        private Instant latestMessageTime;
        private List<UUID> participantUserIds;

        @Builder
        public ChannelSummaryDTO(UUID id, ChannelType type, String name, String description, Instant latestMessageTime, List<UUID> participantUserIds) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.description = description;
            this.latestMessageTime = latestMessageTime;
            this.participantUserIds = participantUserIds;
        }
    }

    @Getter
    public static class ChannelUpdateDTO {
        private UUID id;
        private String name;
        private String description;

        @Builder
        public ChannelUpdateDTO(UUID id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }
}
