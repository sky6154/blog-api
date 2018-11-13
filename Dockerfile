FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

EXPOSE 80

RUN sh -c 'touch /develobeer.jar'

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=live","-jar","/develobeer.jar"]