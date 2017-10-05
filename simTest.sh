!#/bin/bash
count=$1
for ((i = 1; i <= $1; i++));
do
java -jar ~/Project1/simClient.jar $count
done
