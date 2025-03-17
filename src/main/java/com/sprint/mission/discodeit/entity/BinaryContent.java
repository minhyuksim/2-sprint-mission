package com.sprint.mission.discodeit.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class BinaryContent {
    private UUID id;
    private Instant createdAt;
    private String contentType;
    private byte[] content;
    private UUID commonId;

    @Builder
    public BinaryContent(UUID id, String contentType, byte[] content, UUID commonId) {
        this.id = id;
        this.createdAt = Instant.now();
        this.contentType = contentType;
        this.content = content;
        this.commonId = commonId;
    }
}
