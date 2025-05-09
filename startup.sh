#!/bin/sh
echo "Compiling code"
cd /opt/codebase
git pull
echo "Doing from repo"
#mvnw compile
#mvnw exec:java -Dexec.mainClass=com.bits.ss.SsUserServiceApplication