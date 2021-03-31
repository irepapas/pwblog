#!/bin/sh
mvn clean package && docker build -t com.mycompany/blog .
docker rm -f blog || true && docker run -d -p 8080:8080 -p 4848:4848 --name blog com.mycompany/blog 
