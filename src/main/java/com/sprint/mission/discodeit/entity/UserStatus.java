package com.sprint.mission.discodeit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserStatus extends BaseUpdatableEntity{
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Instant lastActiveAt;

    @Builder
    public UserStatus(User user,Instant lastActiveAt) {
        this.user = user;
        this.lastActiveAt = lastActiveAt;

        user.setUserStatus(this);
    }

    public void update(Instant lastActiveAt) {
        if (lastActiveAt != null && !lastActiveAt.equals(this.lastActiveAt)) {
            this.lastActiveAt = lastActiveAt;
        }
    }

    public Boolean isOnline() {
        Instant instantFiveMinutesAgo = Instant.now().minus(Duration.ofMinutes(5));

        return lastActiveAt.isAfter(instantFiveMinutesAgo);
    }
}
