#!/bin/bash
echo "Compiling code"
cd /home/codebase
git pull
mvnw package
mvnw exec:java -Dexec.mainClass=com.bits.ss.SsUserServiceApplication