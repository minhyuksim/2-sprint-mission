package com.sprint.mission.discodeit.entity;

public class User extends Common {
    private String username;

    public User (String username) {
        super();   // UUID, createdAt, updatedAt
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void updateUsername(String username) {
        this.username = username;
        setUpdatedAt(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "User [id :" + id + ", username :" + username + "]";
    }
}
