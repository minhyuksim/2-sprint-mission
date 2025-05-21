FROM amazoncorretto:17

WORKDIR /app

COPY . .

RUN ./gradlew build -x test

ENV PROJECT_NAME=discodeit
ENV PROJECT_VERSION=1.2-M8
ENV JVM_OPTS="-Xms256m -Xmx512m"

EXPOSE 80

ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -jar build/libs/*.jar"]