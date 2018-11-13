FROM openjdk:10-jdk-slim
MAINTAINER kokj <sky6154@gmail.com>

EXPOSE 80

VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","blog.develobeer.api.Application"]