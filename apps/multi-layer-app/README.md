# WIP: multi-layer application

### Architecture
![architecture](docs/multi-layer-app-architecture.svg)

### Build and run
Build and run on local machine.
```
gradle clean installDist 
./app-frontend/build/install/app-frontend/bin/app-frontend
./app-backend/build/install/app-backend/bin/app-backend
```

### Build docker images
```
docker build . -f app-frontend/Dockerfile -t app-frontend:1.0.0-SNAPSHOT
docker build . -f app-backend/Dockerfile -t app-backend:1.0.0-SNAPSHOT
docker save --output="app-frontend/build/app-frontend:1.0.0-SNAPSHOT.tar" app-frontend:1.0.0-SNAPSHOT
docker save --output="app-backend/build/app-backend:1.0.0-SNAPSHOT.tar" app-backend:1.0.0-SNAPSHOT
```
### REST APIs
* ``curl -X GET http://127.0.0.1:8080/status``
* ``curl -d '{ "id": 1234, "data": { "typeId": "java.lang.String", "data": "xxx" }, "capability": "super" }' -X POST http://127.0.0.1:8080/exec``