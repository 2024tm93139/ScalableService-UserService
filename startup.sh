#!/bin/bash
echo "Compiling code"
cd /home/codebase
git pull
mvnw compile
mvnw exec:java -Dexec.mainClass=com.bits.ss.SsUserServiceApplication