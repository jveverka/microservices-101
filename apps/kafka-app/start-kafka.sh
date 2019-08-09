#!/bin/bash

/kafka_2.11-2.3.0/bin/zookeeper-server-start.sh /kafka_2.11-2.3.0/config/zookeeper.properties &>/kafka_2.11-2.3.0/zookeeper.log &
/kafka_2.11-2.3.0/bin/kafka-server-start.sh /kafka_2.11-2.3.0/config/server.properties
