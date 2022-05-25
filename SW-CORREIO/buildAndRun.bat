@echo off
call mvn clean package
call docker build -t com.mycompany/SW-Trab1 .
call docker rm -f SW-Trab1
call docker run -d -p 9080:9080 -p 9443:9443 --name SW-Trab1 com.mycompany/SW-Trab1