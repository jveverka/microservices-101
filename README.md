# Microservices 101
This project is designed to demonstrate building blocks of microservice systems and provide 
hands-on examples of various microservice deployment scenarios for [kubernetes](https://kubernetes.io/) 
All examples are tailored for training and better understanding of distributed microservice systems.

## Example services
Some simple examples to practice micro-service deployments. 
* [Simple REST service](apps/service-simple-rest) - java11, [SpringBoot](https://spring.io/projects/spring-boot)
* [Application cluster](apps/akka-k8s-cluster) for kubernetes - java11, [Akka](https://akka.io/)
* [Multi layer app](apps/multi-layer-app) - microservice app deployment, frontend, backend, [gRPC](https://grpc.io/)
* [Busybox test app](apps/busybox) - pod used to test DNS, and service availability from within the pod.

## Kubernetes cluster
To deploy example services, kubernetes cluster is required. 
[This manual](docs/kubernetes/README.md) based on official kubernetes documentation describes 
how to setup private on-premises k8s cluster for practicing deployments. 

## Build all example apps
``
gradle clean installDist 
``

Enjoy !