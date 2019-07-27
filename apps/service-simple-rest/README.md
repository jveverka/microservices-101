# Simple stateless REST service
This is simple demo of stateless REST service.
it responds to single GET requests.

### Build and run
```
gradle clean build 
java -jar build/libs/service-simple-rest-1.0.0-SNAPSHOT.jar
```

### Build Docker image 
```
docker build . -t service-simple-rest:1.0.0-SNAPSHOT
docker image list
docker save --output="build/service-simple-rest:1.0.0-SNAPSHOT.tar" service-simple-rest:1.0.0-SNAPSHOT
docker image rm -f <imageid>
docker run -p 8888:8080 service-simple-rest:1.0.0-SNAPSHOT
```

### REST endpoint
* __GET__ ``http://hostname:port/data/info``

``curl -X GET http://localhost:8080/data/info``
``curl -X GET http://localhost:8080/data/compute/pi/{precision}`` where precision is in range 0 .. 10, 10 is max. precision

### Kubernetes deployment
[yaml](service-simple-rest-deployment.yaml) file describes deployment into kubernetes cluster. 
```
kubectl apply -f service-simple-rest-deployment.yaml
```

This deploys two instances of REST service behind single __load balancer__.
Load-balanced service is accessible master kubernetes node using URL:
```
for i in {1..1000}; do
   curl -X GET http://<kube-master>:30081/data/info
   echo ""
done   
``` 
