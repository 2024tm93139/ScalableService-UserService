#!/bin/bash
echo "Compiling code"
cd /home/codebase
git pull
mvn package
mvn exec:java -Dexec.mainClass=com.bits.ss.SsUserServiceApplication