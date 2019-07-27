#!/bin/bash

ssh -L8001:127.0.0.1:8001 -i controlkey.pri juraj@192.168.56.101 kubectl proxy

