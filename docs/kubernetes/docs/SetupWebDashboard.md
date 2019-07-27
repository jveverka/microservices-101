# Setup web UI dashboard

## On Kubernetes master node
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta1/aio/deploy/recommended.yaml
kubectl proxy
```

### Create admin user
```
kubectl apply -f dashboard-adminuser.yaml
```
Where the content of ``dashboard-adminuser.yaml`` is
```
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kube-system
```

```
kubectl apply -f dashboard-clustersetup.yaml
```
Where the content of ``dashboard-clustersetup.yaml`` is
```
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kube-system
```

Get admin token:
```
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep admin-user | awk '{print $1}')
```
Use __token__ in web UI login form.

## On your local PC
Kubernetes web UI is accessible only from within kubernetes master node. To connect from outside, ssh tunnel is necessary. 
Create ssh tunnel to kubernetes master web UI like this:
```
ssh -L 8001:127.0.0.1:8001 user@masternode
```
Web UI is than accessible at URL: http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/


