package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.jcf.JCFUserServiece;

import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserServiece userServiece = new JCFUserServiece();

        User user = new User("민혁");
        userServiece.Create(user);
        System.out.println(user.toString());

    }
}
