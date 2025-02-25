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



        while (run) {
            System.out.println("======= 메뉴 =======");
            System.out.println("1. 유저 생성");
            System.out.println("2. 채널 생성");
            System.out.println("3. 메세지 생성");
            System.out.println("4. 사용자 조회(이름)");
            System.out.println("5. 전체 사용자 목록 출력");
            System.out.println("6. 사용자 업데이트");
            System.out.println("7. 프로그램 종료");
            System.out.print("원하는 번호를 선택하세요 : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("이름을 입력해주세요 : ");
                    String username = sc.nextLine();
                    User user = new User(username);
                    userService.Create(user);
                    System.out.println("사용자가 생성 되었습니다.");
                    System.out.print("사용자 정보는 : ");
                    System.out.println(userService.get(user.getId()));
                    break;

                case 2:
                    System.out.print("채널명을 입력주세요 : ");
                    String chname = sc.nextLine();
                    Channel channel = new Channel(chname);
                    channelService.Create(channel);
                    System.out.println("채널이 생성 되었습니다.");
                    System.out.print("채널 정보는 : ");
                    System.out.println(channelService.get(channel.getId()));
                    break;

                case 3:
                    System.out.print("보내시는분의 이름을 입력해주세요 : ");
                    String sendername = sc.nextLine();
                    User sender = userService.getAll().stream()
                            .filter(u -> u.getUsername().equals(sendername))
                            .findFirst().orElse(null);
                    if (sender == null) {
                        System.out.println("보내는 사람이 존재하지 않습니다.");
                        break;
                    }

                    System.out.print("채널명을 입력해주세요 : ");
                    String senderchname = sc.nextLine();
                    Channel senderch = channelService.getAll().stream()
                            .filter(c->c.getChname().equals(senderchname))
                            .findFirst().orElse(null);
                    if (senderch == null) {
                        System.out.println("채널이 존재하지 않습니다.");
                        break;
                    }

                    System.out.print("메세지를 입력해주세요 : ");
                    String mname = sc.nextLine();
                    Message message = new Message(mname, sender.getId(), sender.getId());
                    messageService.Create(message);
                    System.out.println("메세지가 생성되었습니다.");
                    break;



                case 4:
                    System.out.print("찾으시는 카테고리를 입력해주세요(1.유저 2.채널 3.메세지) : ");
                    int catechoice = sc.nextInt();
                    sc.nextLine();

                    switch (catechoice) {
                        case 1:
                            System.out.print("찾는 유저의 성함을 입력해주세요 : ");
                            String findusername = sc.nextLine();
                            User finduser = userService.getAll().stream()
                                    .filter(u -> u.getUsername().equals(findusername))
                                    .findFirst().orElse(null);
                            if (finduser == null) {
                                System.out.println("찾으시는 유저가 존재하지 않습니다.");
                                break;
                            }
                            System.out.println(userService.get(finduser.getId()));
                            break;

                        case 2:
                            System.out.print("찾는 채널 이름을 입력해주세요 : ");
                            String findchname = sc.nextLine();
                            Channel findChannel = channelService.getAll().stream()
                                    .filter(c->c.getChname().equals(findchname))
                                    .findFirst().orElse(null);
                            if (findChannel == null) {
                                System.out.println("채널이 존재하지 않습니다.");
                                break;
                            }
                            System.out.println(channelService.get(findChannel.getId()));
                            break;

                        case 3:
                            System.out.print("찾는 메세지를 입력해주세요 : ");
                    }

                    break;

                case 5:
                    System.out.println("전체 사용자 목록 : ");
                    userService.getAll().forEach(alluser -> System.out.println(alluser));
                    channelService.getAll().forEach(allchannel -> System.out.println(allchannel.toString()));
                    messageService.getAll().forEach(allmessage -> System.out.println(allmessage));
                    break;

                case 6:
                    System.out.print("업데이트할 사용자의 성함을 입력해주세요 : ");
                    String updateUsername = sc.nextLine();
                    User updateUser = userService.getAll().stream()
                            .filter(u -> u.getUsername().equals(updateUsername))
                            .findFirst().orElse(null);
                    if (updateUser == null) {
                        System.out.println("해당 사용자가 존재하지 않습니다.");
                        break;
                    }
                    System.out.print("새로운 사용자 이름을 입력해주세요 : ");
                    String newUsername = sc.nextLine();
                    userService.update(updateUser, newUsername);
                    System.out.println("사용자 업데이트가 완료되었습니다.");
                    System.out.println("업데이트된 사용자 정보 : " + userService.get(updateUser.getId()));
                    break;

                case 7:
                    run = false;
                    break;
            }



        }

    }
}
