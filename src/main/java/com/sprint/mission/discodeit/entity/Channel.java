package com.sprint.mission.discodeit.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Channel extends BaseUpdatableEntity {
    private ChannelType type;
    private String name;
    private String description;

    @Builder
    public Channel(ChannelType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public void update(String newName, String newDescription) {
        if (newName != null && !newName.equals(this.name)) {
            this.name = newName;
        }
        if (newDescription != null && !newDescription.equals(this.description)) {
            this.description = newDescription;
        }
    }
}
