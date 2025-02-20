package com.sprint.mission.discodeit.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFUserServiece implements UserService {
    private final Map<UUID, User> data = new HashMap<>();

    @Override
    public void Create(User user) {
        data.put(user.getId(), user);
    }


}
