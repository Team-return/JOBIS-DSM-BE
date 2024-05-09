FROM openjdk:17-jdk-slim

RUN apt-get update; apt-get install -y fontconfig libfreetype6
EXPOSE 8080
ENV TZ=Asia/Seoul
COPY ./jobis-infrastructure/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
