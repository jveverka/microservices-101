# k8s Consul example
[spring-cloud-consul](https://cloud.spring.io/spring-cloud-consul/reference/html/)

## Run on localhost
* download and install [consul](https://releases.hashicorp.com/consul/1.6.3/consul_1.6.3_linux_amd64.zip)
  ```
  unzip consul_1.6.3_linux_amd64.zip
  ```
* start local consul instance at [http://localhost:8500](http://127.0.0.1:8500/)
  ```
  ./consul agent -dev
  ```