#!/bin/bash

cp target/rest-user-service.jar target/classes/docker
cd target/classes/docker
docker rmi innoq-demo/rest-user-service:latest
docker build --rm -t innoq-demo/rest-user-service:latest .
