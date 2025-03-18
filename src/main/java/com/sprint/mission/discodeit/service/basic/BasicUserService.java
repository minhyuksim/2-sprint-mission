package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserDTO;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(UserDTO.fromUserCreateDTO fromusercreateDTO) {
        if(userRepository.existsByUsername(fromusercreateDTO.getUsername())) {
            throw new IllegalArgumentException("중복이 이름이 있습니다.");
        }
        if(userRepository.existsByEmail(fromusercreateDTO.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일이 있습니다.");
        }

        User user = User.builder()
                .username(fromusercreateDTO.getUsername())
                .email(fromusercreateDTO.getEmail())
                .password(fromusercreateDTO.getPassword())
                .profileId(fromusercreateDTO.getProfileId())
                .build();

        UserStatus userStatus = UserStatus.builder()
                .userId(user.getId())
                .lastLoginAt(Instant.now())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User find(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(UUID userId, String newUsername, String newEmail, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        user.update(newUsername, newEmail, newPassword);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User with id " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }
}
