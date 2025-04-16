package com.sprint.mission.discodeit.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BinaryContent extends BaseEntity {
    private String fileName;
    private Long size;
    private String contentType;
    private byte[] bytes;

    @Builder
    public BinaryContent(String fileName, Long size, String contentType, byte[] bytes) {
        this.id = UUID.randomUUID();
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
    }
}
