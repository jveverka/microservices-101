# Akka k8s cluster demo
This is simple Akka cluster demo tailored for deployment into kubernetes environment.

### Build and run
```
gradle clean installDist 
./build/install/akka-k8s-cluster/bin/akka-k8s-cluster
```
### Build Docker image 
```
docker build . -t akka-k8s-cluster:1.0.0-SNAPSHOT
docker image list
docker save --output="build/akka-k8s-cluster:1.0.0-SNAPSHOT.tar" akka-k8s-cluster:1.0.0-SNAPSHOT
docker image rm -f <imageid>
docker run -p 8558:8558 -p 2552:2552 akka-k8s-cluster:1.0.0-SNAPSHOT
```

## k8s deployment
K8s deploy sequence:
```
kubectl apply -f akka-k8s-cluster-roles.yaml
kubectl apply -f akka-k8s-cluster-deployment.yaml
kubectl logs akka-k8s-cluster-<podId> 
```
K8s undeploy sequence:
```
kubectl delete service/akka-k8s-cluster
kubectl delete deployment.apps/akka-k8s-cluster
```

## Akka management
Akka management endpoints:
* __GET__ http://127.0.0.1:8558/cluster/members
* __GET__ http://127.0.0.1:8558/bootstrap/seed-nodes
