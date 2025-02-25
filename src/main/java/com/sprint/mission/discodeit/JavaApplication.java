package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.jcf.JCFChannelService;
import com.sprint.mission.discodeit.jcf.JCFMessageService;
import com.sprint.mission.discodeit.jcf.JCFUserService;

import java.util.Scanner;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();
        JCFChannelService channelService = new JCFChannelService();
        JCFMessageService messageService = new JCFMessageService();
        Scanner sc = new Scanner(System.in);
        boolean run = true;



        //객체 초기화
        User user1 = new User("민혁");
        User user2 = new User("코드잇");
        Channel channel1 = new Channel("백엔드2기");
        Channel channel2 = new Channel("프론트엔드2기");


        //객체 생성
        userService.Create(user1);
        userService.Create(user2);
        channelService.Create(channel1);
        channelService.Create(channel2);

        Message message = new Message("hi, hi", user1.getId(), channel1.getId());
        messageService.Create(message);

        //단건 조회
        System.out.println(userService.get(user1.getId()));
        System.out.println(channelService.get(channel1.getId()));
        System.out.println(messageService.get(message.getId()));

        //다건 조회
        System.out.println("전체 사용자 목록 : ");
        userService.getAll().forEach(user -> System.out.println(user.toString()));
        channelService.getAll().forEach(channel -> System.out.println(channel.toString()));
        messageService.getAll().forEach(m -> System.out.println(m.toString()));

        // 업데이트 이후 정보 출력
        userService.update(user1, "심민혁");
        userService.getAll().forEach(user -> System.out.println(user.toString()));
        channelService.update(channel1, "백엔드 3기");
        channelService.getAll().forEach(channel -> System.out.println(channel.toString()));
        messageService.update(message, "hello, world");
        messageService.getAll().forEach(m -> System.out.println(m.toString()));

        // 삭제
        boolean userdelete = userService.delete(user1.getId());
        System.out.println("메세지 삭제 여부 : " + userdelete);
        boolean chdelete = channelService.delete(channel1.getId());
        System.out.println("메세지 삭제 여부 : " + chdelete);
        boolean messagedelete = messageService.delete(message.getId());
        System.out.println("메세지 삭제 여부 : " + messagedelete);

        // 삭제 확인
        System.out.println("삭제 확인 후 조회(null일 경우 삭제 완료) : " + userService.get(user1.getId()));
        System.out.println("삭제 확인 후 조회(null일 경우 삭제 완료) : " + channelService.get(channel1.getId()));
        System.out.println("삭제 확인 후 조회(null일 경우 삭제 완료) : " + messageService.get(message.getId()));


    }
}
