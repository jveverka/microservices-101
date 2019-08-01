# Reset kubernetes cluster setup
In case you need to reset k8s cluster setup, follow commands below.

### On k8s Master node
```
sudo kubeadm reset
sudo kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=<IP-ADDR-API>

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/a70459be0084506e4ec919aa1c114638878db11b/Documentation/kube-flannel.yml
```

### On k8s slave nodes
```
sudo kubeadm reset
sudo kubeadm join 192.168.56.101:6443 --token <actual-token> --discovery-token-ca-cert-hash sha256:<actual-hash>
```

### Check cluster status on master
```
sudo kubectl get nodes
```
