#!/bin/sh
mvn clean package && docker build -t com.mycompany/SW-Trab1 .
docker rm -f SW-Trab1 || true && docker run -d -p 9080:9080 -p 9443:9443 --name SW-Trab1 com.mycompany/SW-Trab1