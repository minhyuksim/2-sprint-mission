package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void Create(User user);

    User get(UUID id);

    List<User> getAll();

    void update(User user, String updatename);

    boolean delete(UUID id);

}
