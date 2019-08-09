# WIP: Kafka k8s example

### Create Kafka+Zookeeper Docker image
```
docker build . -t kafka-zookeeper:1.0.0-SNAPSHOT
docker save --output="kafka-zookeeper:1.0.0-SNAPSHOT.tar" kafka-zookeeper:1.0.0-SNAPSHOT

# run kafka + zookeeper image locally
docker run -p 9092:9092 -p 2181:2181 kafka-zookeeper:1.0.0-SNAPSHOT
```
