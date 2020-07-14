package io.github.syamantm.ktream.config;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.avro.UserTransactionAvro;
import java.util.Collections;
import java.util.Map;
import org.apache.kafka.common.serialization.Serde;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchemaRegistryConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchemaRegistryConfig.class);

  @Bean
  public SchemaRegistryClient schemaRegistryClient(
      @Value("${app.schema.registry.url}") String schemaRegistryUrl) {
    LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
    ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
    client.setEndpoint(schemaRegistryUrl);
    return client;
  }

  @Bean
  public Serde<UserAvro> avroUser(
      @Value("${app.schema.registry.url}") String schemaRegistryUrl) {
    LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
    final SpecificAvroSerde<UserAvro> avroInSerde = new SpecificAvroSerde<>();
    final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
        schemaRegistryUrl);
    avroInSerde.configure(serdeConfig, false); // `false` for record values
    return avroInSerde;
  }

  @Bean
  public Serde<TransactionAvro> avroTransaction(
      @Value("${app.schema.registry.url}") String schemaRegistryUrl) {
    LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
    final SpecificAvroSerde<TransactionAvro> avroOutSerde = new SpecificAvroSerde<>();
    final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
        schemaRegistryUrl);
    avroOutSerde.configure(serdeConfig, false); // `false` for record values
    return avroOutSerde;
  }

  @Bean
  public Serde<UserTransactionAvro> avroUserTransaction(
      @Value("${app.schema.registry.url}") String schemaRegistryUrl) {
    LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
    final SpecificAvroSerde<UserTransactionAvro> avroOutSerde = new SpecificAvroSerde<>();
    final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
        schemaRegistryUrl);
    avroOutSerde.configure(serdeConfig, false); // `false` for record values
    return avroOutSerde;
  }
}
