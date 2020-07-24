#!/bin/bash
sudo docker network rm db_subnet
sudo docker stop db
sudo docker rm db

sudo docker build -t database .
sudo docker network create --gateway 172.16.1.1 --subnet 172.16.1.0/24 db_subnet
sudo docker run --net db_subnet --ip 172.16.1.2 --name db -d database
