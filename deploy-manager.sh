#!/bin/sh
{
  echo try to deploy docker stack...
  docker stack deploy -e TAG=$1 --prune --compose-file docker-compose.yml blogApi
}

#docker service create --name blog-api --replicas 2 --publish 80:80 blog-api:latest