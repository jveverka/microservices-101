#!/bin/bash
# deploys docker image into k8s cluster
# pathto image file must be in form of /path/to/image/img-repository:img-tag.tar

IMAGE_FILE_PATH=$1
IMAGE_FILE=`basename $IMAGE_FILE_PATH`
IMAGE_TAG=`basename -s .tar $IMAGE_FILE_PATH`

echo "IMAGE_FILE_PATH=$IMAGE_FILE_PATH"
echo "IMAGE_FILE=$IMAGE_FILE"
echo "IMAGE_TAG=$IMAGE_TAG"

for IP_ADDR in 192.168.56.101 192.168.56.102 192.168.56.103; do
    echo "processing IP: $IP_ADDR"
    scp -i controlkey.pri $IMAGE_FILE_PATH juraj@$IP_ADDR:/opt/images/
    ssh -i controlkey.pri juraj@$IP_ADDR docker image rm $IMAGE_TAG
    ssh -i controlkey.pri juraj@$IP_ADDR docker load --input /opt/images/$IMAGE_FILE
done


