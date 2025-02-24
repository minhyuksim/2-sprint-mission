package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.jcf.JCFUserService;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();


        //user 객체 초기화
        User user1 = new User("민혁");
        User user2 = new User("코드잇");

        //user 객체 생성
        userService.Create(user1);
        userService.Create(user2);

        //user 단건 조회
        System.out.println(userService.get(user1.getId()));

        // user 다건 조회
        System.out.println("전체 사용자 목록 : ");
        userService.getAll().forEach(user -> System.out.println(user.toString()));

        // 업데이트 이후 정보 출력
        userService.update(user1, "심민혁");
        userService.getAll().forEach(user -> System.out.println(user.toString()));

        // 삭제
        boolean delete = userService.delete(user1.getId());
        System.out.println("메세지 삭제 여부 : " + delete);

        // 삭제 확인
        System.out.println("삭제 확인 후 조회(null일 경우 삭제 완료) : " + userService.get(user1.getId()));

    }
}
