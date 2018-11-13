#!/bin/sh
{
  echo try remove old images...
  docker rmi -f blog-api:latest
}
{
  echo try system prune...
  docker system prune -f
}

docker load < blog-api.tar