FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

EXPOSE 80

ARG JAR_FILE

COPY ${JAR_FILE} develobeer.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=live","-jar","/develobeer.jar"]