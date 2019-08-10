#!/bin/bash

echo "KAFKA_HOME=${KAFKA_HOME}"
java -version
hostname
echo "HOSTNAME=${HOSTNAME}"

${KAFKA_HOME}/bin/zookeeper-server-start.sh ${KAFKA_HOME}/config/zookeeper.properties &>${KAFKA_HOME}/zookeeper.log &
echo "zookeeper started"
${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/server.properties
