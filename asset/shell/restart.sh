#!/bin/bash
PID=$(cat /media/data/eric/app/print-user/print_server.pid)
nohup kill -9 $PID
export JAVA_HOME=/opt/eric/jbr/
export PATH=$JAVA_HOME/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin
nohup java -jar /media/data/eric/app/print-user/print-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod -Xmx1G -Xms512M -server -XX:+UseG1GC >>/media/data/eric/app/print-user/print.log  2>&1 &
echo $! > /media/data/eric/app/print-user/print_server.pid