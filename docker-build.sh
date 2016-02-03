#!/bin/bash

cp target/rest-user-service.jar target/classes/docker
cd target/classes/docker
sudo docker rmi devstation/rest-user-service:latest
sudo docker build --rm -t devstation/rest-user-service:latest .
