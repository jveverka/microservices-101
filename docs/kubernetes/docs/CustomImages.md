# Using custom docker images

Once you have created your custom docker image, you need to import it into docker on each node in kubernetes cluster.
```
scp my-image\:1.0.0-SNAPSHOT.tar user@kube-node:/opt/images
cd /opt/images
docker load --input my-image\:1.0.0-SNAPSHOT.tar 
docker image ls 
```

To remove docker image:
```
docker image rm <imageId>
```

