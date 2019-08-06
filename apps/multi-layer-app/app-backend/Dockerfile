FROM openjdk:11-slim
ADD app-backend/build/install/* /app-backend/
ADD app-backend/start-backend.sh /app-frontend/
ENTRYPOINT ["/app-frontend/start-backend.sh"]
