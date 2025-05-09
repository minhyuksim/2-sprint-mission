package com.sprint.mission.discodeit.exception.User;

import com.sprint.mission.discodeit.exception.ErrorCode;

import java.util.Map;

public class UserAlreadyExistException extends UserException {
    public UserAlreadyExistException(String username, String email) {
        super(ErrorCode.DUPLICATE_USER, Map.of(
                "username", username,
                "email", email
        ));
    }
}