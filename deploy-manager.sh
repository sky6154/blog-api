#!/bin/sh
{
  echo try to deploy docker stack...
  # -e 옵션이 지원되지 않기 때문에, .env 파일을 생성하기 보다 도커 명령어 실행 전에 앞에 변수를 넘기면, .env 파일처럼 사용 가능하다.
  TAG=$1 docker stack deploy --prune --compose-file docker-compose.yml blogApi
}

#docker service create --name blog-api --replicas 2 --publish 80:80 blog-api:latest