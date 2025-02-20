package com.sprint.mission.discodeit.entity;

public class User extends Common {
    private String name;

    User (String name){
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name){
        this.name = name;
        setUpdatedAt();
    }

    @Override
    public String toString() {
        return "User{id=" + getId() + ", name='" + name + "', createdAt=" + getCreatedAt() + ", updatedAt=" + getUpdatedAt() + "}";
    }

}
