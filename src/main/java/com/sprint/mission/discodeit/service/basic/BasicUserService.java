package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserDTO;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
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
    private final UserStatusRepository userStatusRepository;
    private final BinaryContentRepository binaryContentRepository;

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
        userRepository.save(user);

        UserStatus userStatus = UserStatus.builder()
                .userId(user.getId())
                .lastLoginAt(Instant.now())
                .build();
        userStatusRepository.save(userStatus);

        return userRepository.save(user);
    }

    @Override
    public UserDTO.UserFindDTO find(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        UserStatus userstatus = userStatusRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        UserDTO.UserFindDTO findDTO = UserDTO.UserFindDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .online(userstatus.isOnline())
                .build();

        return findDTO;
    }

    @Override
    public List<UserDTO.UserFindDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user->{
                    boolean isOnline = userStatusRepository.findById(user.getId())
                            .map(UserStatus::isOnline)
                            .orElse(false);

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
    public User update(UserDTO.UserUpdateDTO fromuserupdateDTO) {
        User user = userRepository.findById(fromuserupdateDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("User with id " + fromuserupdateDTO.getId() + " not found"));
        user.update(fromuserupdateDTO.getUsername(),
                fromuserupdateDTO.getEmail(),
                fromuserupdateDTO.getPassword(),
                fromuserupdateDTO.getProfileId());
        userRepository.save(user);

        UserStatus updatedStatus = userStatusRepository.findById(user.getId())
                        .orElseThrow(NoSuchElementException::new);
        updatedStatus.setLastLoginAt(Instant.now());
        userStatusRepository.save(updatedStatus);
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (user.getProfileId() != null) {
            binaryContentRepository.deleteById(user.getProfileId());
        }
        userStatusRepository.deleteById(userId);
        userRepository.deleteById(userId);
    }
}
