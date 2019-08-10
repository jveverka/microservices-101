# WIP: Kafka k8s example

### Create Kafka+Zookeeper Docker image
```
docker build . -f Dockerfile -t kafka-zookeeper:2.11_2.3.0
docker save --output="build/kafka-zookeeper:2.11_2.3.0.tar" kafka-zookeeper:2.11_2.3.0

# run kafka + zookeeper image locally
docker run -p 2181:2181 -p 9092:9092 --hostname=127.0.0.1 --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 kafka-zookeeper:2.11_2.3.0
```

### k8s deployment
```
kubectl apply -f kafka-zookeeper-deployment.yaml
``` 

#### Undeploy multi-layer application
```
# undeploy kafka-zookeeper
kubectl delete service/kafka-lb deployment.apps/kafka-zookeeper
```