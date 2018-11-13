FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

VOLUME /tmp

#ARG JAR_FILE
#COPY ${JAR_FILE} develobeer.jar
#
EXPOSE 80

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","blog.develobeer.api.ApiApplication"]

# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.sprofiles.active=live","-jar","/develobeer.jar"]