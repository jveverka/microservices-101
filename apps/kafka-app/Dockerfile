FROM openjdk:11-slim

ENV DEBIAN_FRONTEND noninteractive
ENV SCALA_VERSION 2.11
ENV KAFKA_VERSION 2.3.0
ENV KAFKA_HOME /kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"

ADD http://tux.rainside.sk/apache/kafka/2.3.0/kafka_2.11-2.3.0.tgz /
RUN tar xzvf /kafka_2.11-2.3.0.tgz
RUN rm /kafka_2.11-2.3.0.tgz
COPY start-kafka.sh /
EXPOSE 2181 9092

ENTRYPOINT [ "/start-kafka.sh" ]
