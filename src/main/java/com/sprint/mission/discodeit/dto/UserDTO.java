package com.sprint.mission.discodeit.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class UserDTO {

    @Getter
    public static class fromUserCreateDTO {
        private String username;
        private String email;
        private String password;
        private UUID profileId;

        @Builder
        public fromUserCreateDTO(String username, String email, String password, UUID profileId) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.profileId = profileId;
        }
    }

    //public static
}

