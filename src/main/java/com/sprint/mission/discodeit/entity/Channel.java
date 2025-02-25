package com.sprint.mission.discodeit.entity;

public class Channel extends Common {
    private String Chname;

    public Channel(String Chname) {
        super();
        this.Chname = Chname;
    }



    public void updateChname(String Chname) {
        this.Chname = Chname;
        setUpdatedAt(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "[CHname : " + Chname + ", ID : " + id + " 생성시간 : " + getCreatedAt() + " 업데이트 시간 : "+ getUpdatedAt() + "]";
    }



}
