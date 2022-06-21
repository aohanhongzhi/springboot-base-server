#!/bin/bash
mvn clean package -Dmaven.test.skip=true
scp target/rblc-spf.jar root@119.3.136.251:/home/RBLC
ssh root@119.3.136.251  sh /home/RBLC/start.sh
