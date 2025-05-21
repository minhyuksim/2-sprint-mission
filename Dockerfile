FROM amazoncorretto:17

WORKDIR /app

COPY . .

RUN ./gradlew clean booJar -x test

ENV JVM_OPTS="-Xms256m -Xmx512m"

EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -jar build/libs/discodeit-1.2-M8.jar"]
