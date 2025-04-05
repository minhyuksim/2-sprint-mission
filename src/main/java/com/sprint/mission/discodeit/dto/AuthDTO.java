package com.sprint.mission.discodeit.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthDTO {

    @Getter
    public static class LoginDTO {
        private String username;
        private String password;

        @Builder
        public LoginDTO(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

}
