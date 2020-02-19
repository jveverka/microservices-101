[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/jveverka/microservices-101.svg?branch=master)](https://travis-ci.org/jveverka/microservices-101)

# Microservices 101
This training demo is designed to demonstrate building blocks of microservice systems and provide 
hands-on examples of various microservice deployment scenarios for [kubernetes](https://kubernetes.io/). 
All examples are tailored for training and better understanding of distributed microservice systems.

## 1. Setup Kubernetes cluster
To deploy example services, kubernetes cluster is required. 
[This manual](docs/kubernetes/README.md) based on official kubernetes documentation describes 
how to setup private on-premises k8s cluster for practicing deployments of example services below. 

## 2. Build example services
To build all example java applications, run command. 
Please make sure that your [environment is setup](docs/environment-setup.md) properly.
```
gradle clean build installDist 
```

## 3. Setup local docker registry
Setup [local docker registry](docs/local-docker-registry.md) in order to deploy example applications. 

## 4. Learn to deploy applications
Simple example services (applications) to practice micro-service deployments into k8s cluster. 
Follow each example exercise to get hands-on experience with k8s deployments. 
* __Example 1:__ [Simple REST service](apps/service-simple-rest) - java11, [SpringBoot](https://spring.io/projects/spring-boot), http/REST 
* __Example 2:__ [Busybox test app](apps/busybox) - pod used to test DNS, and service availability from within the pod.
* __Example 3:__ [Application cluster](apps/akka-k8s-cluster) for kubernetes - java11, [Akka](https://akka.io/), TCP, http/REST
* __Example 4:__ [Multi layer app](apps/multi-layer-app) - microservice app deployment, frontend, backend, [gRPC](https://grpc.io/), http/REST

## 5. Explore Microservice Technologies
Explore [microservice architectural patterns](docs/microservice-architecture.md) and [technologies](docs/microservice-ecosystem.md).

_Enjoy !_