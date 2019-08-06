#!/usr/bin/env bash
echo "FRONTEND_HOSTNAME=${FRONTEND_HOSTNAME}"
echo "POD_IP=${POD_IP}"
echo "CAPABILITY=${CAPABILITY}"

/app-backend/bin/app-backend -m ${FRONTEND_HOSTNAME} -o 30556 -a ${POD_IP} -c ${CAPABILITY}
