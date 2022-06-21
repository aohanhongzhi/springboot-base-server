#!/bin/bash
./gradlew clean bootJar -x test
scp target/rblc-spf.jar root@119.3.136.251:/home/RBLC
scp asset/shell/restart.sh insite@file.cupb.top:/home/insite/app/
ssh root@119.3.136.251  sh /home/RBLC/start.sh
