FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

VOLUME /tmp

ARG JAR_FILE
COPY ${JAR_FILE} develobeer.jar

EXPOSE 80

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.sprofiles.active=live","-jar","/develobeer.jar"]