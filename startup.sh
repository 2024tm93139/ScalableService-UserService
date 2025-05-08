#!/bin/bash
echo "Compiling code"
cd /codebase
mvn clean install
echo "Running app"
mvn exec:java -Dexec.mainClass=com.bits.ss.SsUserServiceApplication