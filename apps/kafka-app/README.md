# WIP: Kafka k8s example

### Create Kafka+Zookeeper Docker image
```
docker build . -t kafka-zookeeper:1.0.0-SNAPSHOT
docker save --output="kafka-zookeeper:1.0.0-SNAPSHOT.tar" kafka-zookeeper:1.0.0-SNAPSHOT

# run kafka + zookeeper image locally
docker run -p 2181:2181 -p 9092:9092 --hostname=127.0.0.1 --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 kafka-zookeeper:1.0.0-SNAPSHOT
```
