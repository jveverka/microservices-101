#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
#VOLUME /tmp
COPY build/libs/service-simple-rest-1.0.0-SNAPSHOT.jar /service-simple-rest-1.0.0-SNAPSHOT.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-simple-rest-1.0.0-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-Xms32m", "-Xms128M", "-jar","/service-simple-rest-1.0.0-SNAPSHOT.jar"]
