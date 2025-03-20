package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserDTO;
import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(UserDTO.UserCreateDTO fromusercreateDTO);
    UserDTO.UserFindDTO find(UUID userId);
    List<UserDTO.UserFindDTO> findAll();
    User update(UserDTO.UserUpdateDTO fromuserupdateDTO);
    void delete(UUID userId);
}
