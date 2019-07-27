# Helper scripts k8s ckuster
Some helper scripts for 3-node kubernetes cluster.
Assumptions: 
* generate control keypair
  ```
  ssh-keygen -f controlkey
  mv controlkey controlkey.pri
  ```
* __controlkey.pub__ is distributed on all k8s nodes for root and k8s user.
* ssh root login using key is allowed on all k8s nodes
* master node is 192.168.56.101
* worker 01 node is 192.168.56.102
* worker 02 node is 192.168.56.103

