#!/bin/sh
{
  echo try remove old services...
  docker service rm blog-api
}
{
  echo try remove old images...
  docker rmi -f blog-api:latest
}
{
  echo try system prune...
  docker system prune -f
}

docker load < blog-api.tar
docker service create --name blog-api --replicas 2 --publish 80:80 blog-api:latest