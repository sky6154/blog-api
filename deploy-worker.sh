#!/bin/sh
{
  docker rmi -f blog-api:latest
}
{
  docker system prune -f
}

docker load < blog-api.tar