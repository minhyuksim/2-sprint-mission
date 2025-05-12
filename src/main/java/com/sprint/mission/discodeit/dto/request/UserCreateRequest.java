package com.sprint.mission.discodeit.dto.request;

import jakarta.validation.constraints.*;

public record UserCreateRequest(
    @NotNull(message = "유저이름을 반드시 입력해야 합니다.")
    @NotBlank(message = "이름에 공백이 있으면 안됩니다.")
    String username,

    @NotNull(message = "이메일을 반드시 입력해야 합니다.")
    @Email(message = "유효한 이메일 양식이 아닙니다.")
    String email,

    @NotNull(message = "비밀번호를 반드시 입력해야 합니다.")
    String password
) {

}
