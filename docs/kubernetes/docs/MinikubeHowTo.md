## Minikube
[Minikube](https://github.com/kubernetes/minikube) is single-node sandbox for quick k8s prototyping. Installation is really easy:
```
sudo apt-get update && sudo apt-get install -y apt-transport-https
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list
sudo apt-get update
sudo apt-get install -y kubectl
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v1.2.0/minikube-linux-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube
```
Start minikube. On Linux, no vm-driver is required.
```
sudo minikube start --vm-driver=none
sudo minikube dashboard
```
Follow this [setup guide](https://kubernetes.io/docs/setup/learning-environment/minikube/).
