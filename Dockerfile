FROM openjdk:10-jdk

MAINTAINER kokj <sky6154@gmail.com>

EXPOSE 80

# The application's jar file
ARG JAR_FILE=target/develobeer.jar

# Add the application's jar to the container
ADD ${JAR_FILE} develobeer.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=live","-jar","/develobeer.jar"]