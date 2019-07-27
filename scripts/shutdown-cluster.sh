#!/bin/bash

ssh -i controlkey.pri root@192.168.56.103 poweroff
ssh -i controlkey.pri root@192.168.56.102 poweroff
ssh -i controlkey.pri root@192.168.56.101 poweroff

