FROM openjdk:11-slim
ADD http://tux.rainside.sk/apache/kafka/2.3.0/kafka_2.11-2.3.0.tgz /
RUN tar xzvf /kafka_2.11-2.3.0.tgz
RUN rm /kafka_2.11-2.3.0.tgz
COPY start-kafka.sh /
ENTRYPOINT [ "/start-kafka.sh" ]
