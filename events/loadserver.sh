#!/bin/bash
sudo docker stop server
sudo docker rm server

sudo docker build -t server .
sudo docker run --net db_subnet --ip 172.16.1.3 --name server -d server
