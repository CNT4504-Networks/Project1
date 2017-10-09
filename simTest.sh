#!/bin/bash
count=$1
echo start=$SECONDS seconds
for ((i = 1; i <= $1; i++));
do
java -jar ~/Project1/simClient.jar $count
done
echo duration=$(( SECONDS - start )) seconds
