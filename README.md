# 2-sprint-mission
2기 스프린트 미션 제출 리포지토리입니다.

# [ ] JavaApplication과 DiscodeitApplication에서 Service를 초기화하는 방식의 차이에 대해 다음의 키워드를 중심으로 정리해보세요.\

DiscodeitApplication에서는 SpringApplication.run()을 하는 순간 Spring이 IoC Container를 초기화 하고 @Service같은 어노테이션으로 빈들을 정의 한다.
또한, DiscodeitApplication에서는 BasicService들이 의존성을 주입할때 @RequiredArgsConstructor같은 lombok 어노테이션으로 의존성주입을 자동화하고, @Autowired
어노테이션을 사용하여 Service들의 객체들이 자동으로 IoC Container에 주입된다. 