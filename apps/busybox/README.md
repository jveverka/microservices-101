# busybox pod
This pod is used to exec simple commands from within the busybox pod.
That is useful when checking DNS setup in k8s cluster.

```
kubectl exec busybox nslookup service-name.name-space.svc.cluster.local
kubectl exec busybox cat /etc/resolv.conf
kubectl exec curl http://service-name.name-space.svc.cluster.local:<port>/uri
```