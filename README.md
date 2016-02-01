# RESt User Service (Demo Project for Article)

This project is used to setup CI/CD systems for an article about *Continuous Delivery*.

## Build & Dockerize

To build the project just run:

    mvn clean package

Afterwards the service can be dockered calling

    ./docker-build.sh

To start the container just run

    ./docker.run.sh


## Start without Docker

    mvn clean compile spring-boot:run


## RESt API Endpoints & Usage

### cURL Examples

Following examples assume a local docker-machine ip of _192.168.99.100_ - you might want to change this.

#### get a list of users:
  
    curl http://192.168.99.100:8080/user/
    
#### get a specific user (here: "admin")

    curl http://192.168.99.100:8080/user/admin

#### check password strength

    curl -X POST --data "abc" http://192.168.99.100:8080/user/checkpassword

#### create a new user
 
    curl -X POST -H 'Content-type:application/json' --data "{\"name\":\"admin\",\"password\":\"nimda\"}" http://192.168.99.100:8080/user/create