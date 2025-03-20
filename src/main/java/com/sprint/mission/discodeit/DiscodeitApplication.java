package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.dto.AuthDTO;
import com.sprint.mission.discodeit.dto.UserDTO;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.AuthService;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiscodeitApplication {

	@Autowired
	UserService userService;
	@Autowired
	ChannelService channelService;
	@Autowired
	MessageService messageService;
	@Autowired
	AuthService authService;

	static User setupUser(UserService userService) {
		UserDTO.UserCreateDTO userCreateDTO = UserDTO.UserCreateDTO.builder()
				.username("woody")
				.email("woody@codeit.com")
				.password("woody1234")
				.build();

		User user = userService.create(userCreateDTO);
		return user;
	}

	static AuthDTO.LoginDTO testsetupAuthDTO() {
		AuthDTO.LoginDTO loginDTO = AuthDTO.LoginDTO.builder()
				.username("woody")
				.password("woody1234")
				.build();
		return loginDTO;
	}

	static Channel setupChannel(ChannelService channelService) {
		Channel channel = channelService.create(ChannelType.PUBLIC, "공지", "공지 채널입니다.");
		return channel;
	}

	static void messageCreateTest(MessageService messageService, Channel channel, User author) {
		Message message = messageService.create("안녕하세요.", channel.getId(), author.getId());
		System.out.println("메시지 생성: " + message.getId());
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(DiscodeitApplication.class, args);
		// 서비스 초기화
		UserService userService = context.getBean(UserService.class);
		ChannelService channelService = context.getBean(ChannelService.class);
		MessageService messageService = context.getBean(MessageService.class);
		AuthService authService = context.getBean(AuthService.class);

		// 셋업
		User user = setupUser(userService);
		Channel channel = setupChannel(channelService);
		User loginuser = authService.login(testsetupAuthDTO());
		// 테스트
		messageCreateTest(messageService, channel, user);
		System.out.println(userService.find(user.getId()));
		System.out.println(userService.find(loginuser.getId()));
	}

}
