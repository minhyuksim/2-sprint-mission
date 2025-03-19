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

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(UserDTO.UserCreateDTO fromusercreateDTO) {
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
    public UserDTO.UserFindDTO find(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        boolean isOnline = Instant.now().minusSeconds(300).isBefore(user.getUpdatedAt());

        UserDTO.UserFindDTO findDTO = UserDTO.UserFindDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .online(isOnline).build();

        return findDTO;
    }

    @Override
    public List<UserDTO.UserFindDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user->{
                    boolean isOnline = Instant.now().minusSeconds(300).isBefore(user.getUpdatedAt());

                    return UserDTO.UserFindDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .online(isOnline)
                            .email(user.getEmail())
                            .build();
                })
                .toList();
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
