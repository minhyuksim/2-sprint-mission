package com.sprint.mission.discodeit.entity;

public class User extends Common {
    private String username;

    public User (String username) {
        super();   // UUID, createdAt, updatedAt
        this.username = username;
    }

    public void updateUsername(String username) {
        this.username = username;
        setUpdatedAt(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "[username : " + username + ", ID : " + id + " 생성시간 : " + getCreatedAt() + " 업데이트 시간 : "+ getUpdatedAt() + "]";
    }
}
