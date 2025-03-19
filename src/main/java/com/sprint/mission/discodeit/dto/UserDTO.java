package com.sprint.mission.discodeit.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDTO {

    @Getter
    public static class UserCreateDTO {
        private String username;
        private String email;
        private String password;
        private UUID profileId;

        @Builder
        public UserCreateDTO(String username, String email, String password, UUID profileId) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.profileId = profileId;
        }

    }

    @Getter
    public static class UserFindDTO {
        private UUID id;
        private String username;
        private String email;
        private boolean online;

        @Builder
        public UserFindDTO(UUID id, String username, String email, boolean online) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.online = online;
        }

        @Override
        public String toString() {
            return "FindDTO{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", online=" + online +
                    '}';
        }

    }




}

