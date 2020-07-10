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

### All messages
```bash
zoe  --output table --cluster default topics consume output
```
### Filter messages
Filter message with key `foo1`.

```bash
zoe  --output table --cluster default topics consume output --filter "key == 'foo1'"
```

