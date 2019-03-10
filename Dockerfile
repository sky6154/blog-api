FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

VOLUME /tmp
COPY /build/libs/develobeer-latest.jar develobeer.jar

EXPOSE 80

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=live","-Dserver.port=80","-jar","/develobeer.jar"]