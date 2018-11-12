#!/bin/sh
{
  docker service rm blog-api
}
{
  docker rmi -f blog-api:latest
}
{
  docker system prune -f
}

docker load < blog-api.tar
docker service create --name blog-api --replicas 2 --publish 80:80 blog-api:latest