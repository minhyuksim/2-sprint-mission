package com.sprint.mission.discodeit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class UserStatus implements Serializable {
    private static final Long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLoginAt;

    @Builder
    public UserStatus(UUID userId, Instant lastLoginAt) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.lastLoginAt = lastLoginAt;
    }

    public boolean isOnline() {
        return lastLoginAt != null && Instant.now().minusSeconds(300).isBefore(lastLoginAt);
    }
}
