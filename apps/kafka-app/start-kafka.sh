#!/bin/bash

echo "KAFKA_HOME=${KAFKA_HOME}"
java -version
hostname
echo "HOSTNAME=${HOSTNAME}"

/kafka_2.11-2.3.0/bin/zookeeper-server-start.sh /kafka_2.11-2.3.0/config/zookeeper.properties &>/kafka_2.11-2.3.0/zookeeper.log &
echo "zookeeper started"
/kafka_2.11-2.3.0/bin/kafka-server-start.sh /kafka_2.11-2.3.0/config/server.properties
