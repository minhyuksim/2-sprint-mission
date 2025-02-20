package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public abstract class Common {
    protected final UUID id;
    protected final long createdAt;
    protected long updatedAt;

        public Common () {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
        }

    public UUID getId() {
            return id;
    }

    public long getCreatedAt() {
            return createdAt;
    }

    public long getUpdatedAt() {
            return updatedAt;
    }

    public void setUpdatedAt() {
            this.updatedAt = System.currentTimeMillis();
    }


}
