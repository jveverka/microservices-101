# Create and manage Custom Deployment

Once you have uploaded all your custom docker images on all kubernetes cluster nodes, you may start to create your deployments.
Copy deployment yaml file on kubernetes master into /opt/deployments.

Create deployment:
```
cd /opt/deployments
kubectl apply -f service-simple-rest-deployment.yaml
kubectl describe deployments
kubectl describe deployment simple-rest
```

Remove deployment:
```
# execute get all
kubectl get all

# remove deployment.apps/simple-rest-service
kubectl delete deployment.apps/simple-rest
kubectl delete service/simple-rest
```
