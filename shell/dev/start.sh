#!/bin/bash

## Fix By Server
HOME_DIR=/home/webdev

## Edit By Project
WAR_DIR_NM=BaseFront
WAR_FILE_NM=BaseFront-0.0.1-SNAPSHOT.war

## Fix
WAR_DIR=$HOME_DIR/$WAR_DIR_NM

## Rest Fixed
echo "########## Move WAR_DIR ##########"
cd "$WAR_DIR"

echo "========== Run shutdown.sh =========="
./shutdown.sh

echo "########## java -jar Run ##########"
#java -jar $WAR_FILE_NM --spring.profiles.active=dev &
nohup java -jar $WAR_FILE_NM --spring.profiles.active=dev > /dev/null 2>&1 &
