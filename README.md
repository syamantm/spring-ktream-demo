# Spring KStream Demo
Spring boot + KStream + join with KTable

## Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/gradle-plugin/reference/html/#build-image)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Guides
The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


## Run
```bash
docker-compose up -d
./gradlew clean bootRun
```

## Produce messages
```bash
curl -v -X POST -H"Content-type: application/json" -d '{"key": "foo1", "value":"bar"}' http://localhost:8080/kv-stream/send
curl -v -X POST -H"Content-type: application/json" -d '{"key": "foo2", "value":"bar"}' http://localhost:8080/kv-stream/send
```
## Consume messages
Uee [zoe](https://adevinta.github.io/zoe/).

### Zoe config 

```yaml
clusters:
  default:
    props:
      bootstrap.servers: "localhost:29092"
      key.deserializer: "org.apache.kafka.common.serialization.StringDeserializer"
      value.deserializer: "io.confluent.kafka.serializers.KafkaAvroDeserializer"
      key.serializer: "org.apache.kafka.common.serialization.StringSerializer"
      value.serializer: "io.confluent.kafka.serializers.KafkaAvroSerializer"
      default.key.serde: "org.apache.kafka.common.serialization.Serdes$StringSerde"
      default.value.serde: "io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde"

    registry: "http://localhost:8082"

    topics:
      input:
        name: "input-events-topic"
        subject: "input-events-topic-value"

runners:
  default: "local"
```

### All messages
```bash
zoe  --output table --cluster default topics consume output
```


