package com.sprint.mission.discodeit.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserStatus {
    private UUID id;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLoginAt;

    @Builder
    public UserStatus(UUID id, UUID userId, Instant lastLoginAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.lastLoginAt = lastLoginAt;
    }
}
