#!/usr/bin/env bash
echo "FRONTEND_HOSTNAME=${FRONTEND_HOSTNAME}"
echo "FRONTEND_PORT=${FRONTEND_PORT}"
echo "POD_IP=${POD_IP}"
echo "CAPABILITY=${CAPABILITY}"

/app-backend/bin/app-backend -m ${FRONTEND_HOSTNAME} -o ${FRONTEND_PORT} -a ${POD_IP} -c ${CAPABILITY}
