# k8s Consul example
[Spring-cloud-consul](https://cloud.spring.io/spring-cloud-consul/reference/html/) + [consul.io](https://consul.io)

## Run on localhost
* download and install [consul](https://releases.hashicorp.com/consul/1.7.0/consul_1.7.0_linux_amd64.zip)
  ```
  unzip consul_1.7.0_linux_amd64.zip
  ```
* start local consul instance at [http://localhost:8500](http://127.0.0.1:8500/)
  ```
  ./consul agent -dev
  ```
* create consul key/value configuration
  ```
  Key: /config/myConsulApp/my/prop Value: value-from-consul
  ```
* build project
  ```
  gradle clean build
  ```  
* start consul-frontend application
  ```
  java -jar consul-frontend/build/libs/consul-frontend-1.0.0-SNAPSHOT.jar 
  ```
* try application endpoints
  ```
  GET http://localhost:8080/services/data
  GET http://localhost:8080/services/health
  ```