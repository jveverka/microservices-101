FROM openjdk:11-slim

ENV DEBIAN_FRONTEND noninteractive
ENV SCALA_VERSION 2.11
ENV KAFKA_VERSION 2.3.0
ENV KAFKA_HOME /kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"

ADD http://tux.rainside.sk/apache/kafka/$KAFKA_VERSION/kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz /
RUN tar xzvf /kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz
RUN rm /kafka_$SCALA_VERSION-$KAFKA_VERSION.tgz
COPY start-kafka.sh /
EXPOSE 2181 9092

ENTRYPOINT [ "/start-kafka.sh" ]
