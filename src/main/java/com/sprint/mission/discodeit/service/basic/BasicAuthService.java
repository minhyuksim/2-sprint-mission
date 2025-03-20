package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.AuthDTO;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasicAuthService implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User login(AuthDTO.LoginDTO loginDTO) {

        return userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(loginDTO.getUsername()))
                .filter(user -> user.getPassword().equals(loginDTO.getPassword()))
                .findFirst().orElseThrow(() -> new RuntimeException("로그인 실패 : 아이디와 비밀번호를 다시 확인해주세요."));
    }
}
