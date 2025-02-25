package com.sprint.mission.discodeit.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {
    private final Map<UUID, User> data = new HashMap<>();

    @Override
    public void Create(User user) {
        data.put(user.getId(), user);
    }

    @Override
    public User get(UUID id) {
        return data.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(data.values());
    }

    public void update(User user, String updatename) {
        user.updateUsername(updatename);
    }

    public boolean delete(UUID id) {
        return data.remove(id) != null;
    }

}
