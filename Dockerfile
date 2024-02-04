
FROM openjdk:17.0.2-jdk-slim-buster AS builder

WORKDIR /app
COPY gradlew ./
COPY build.gradle ./
COPY settings.gradle ./
#gradle 복사
COPY gradle ./gradle
COPY src/main ./src/main

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar
# 애플리케이션 빌드
RUN ./gradlew build



# 런타임 단계
FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /app
# 빌드 단계에서 생성된 JAR 파일을 복사
COPY --from=builder /app/build/libs/ProjectTeam-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE="dev"

# 애플리케이션 실행
ENTRYPOINT java -jar app.jar --spring.profiles.active=$PROFILE
