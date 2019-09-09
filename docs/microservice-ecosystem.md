# Microservice ecosystem
When building microservice architecture and deployment, typically following features has to be covered.

| feature / functionality     | technology stack                                             | 
|-----------------------------|--------------------------------------------------------------|
| message broker              | [kafka](https://kafka.apache.org/)                           |
| config. management          | [zookeeper](https://zookeeper.apache.org/)                   |
| distributed synchronization | [zookeeper](https://zookeeper.apache.org/)                   |
| infra monitoring            | [prometheus](https://prometheus.io/)                         |
| infra visibility            | [grafana](https://grafana.com/)                              |
| circuit braker              | [resilience4j](https://github.com/resilience4j/resilience4j) |
| service discovery, mesh     | [consul](https://www.consul.io/)                             |
| service mesh                | [istio](https://istio.io/)                                   |
| spring native               | [SpringCloud](https://spring.io/projects/spring-cloud)       |
| service registry            | [Netflix Eureka](https://github.com/Netflix/eureka)          |

