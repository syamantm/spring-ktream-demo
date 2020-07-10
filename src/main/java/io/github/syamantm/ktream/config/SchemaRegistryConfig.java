package io.github.syamantm.ktream.config;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import io.github.syamantm.avro.KeyValueInputAvro;
import io.github.syamantm.avro.KeyValueOutputAvro;
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
    public SchemaRegistryClient schemaRegistryClient(@Value("${app.schema.registry.url}") String schemaRegistryUrl) {
        LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(schemaRegistryUrl);
        return client;
    }

    @Bean
    public Serde<KeyValueInputAvro> avroKeyValueInput(@Value("${app.schema.registry.url}") String schemaRegistryUrl) {
        LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
        final SpecificAvroSerde<KeyValueInputAvro> avroInSerde = new SpecificAvroSerde<>();
        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
            schemaRegistryUrl);
        avroInSerde.configure(serdeConfig, false); // `false` for record values
        return avroInSerde;
    }

    @Bean
    public Serde<KeyValueOutputAvro> avroKeyValueOutput(@Value("${app.schema.registry.url}") String schemaRegistryUrl) {
        LOGGER.info("app.schema.registry.url: {}", schemaRegistryUrl);
        final SpecificAvroSerde<KeyValueOutputAvro> avroOutSerde = new SpecificAvroSerde<>();
        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
            schemaRegistryUrl);
        avroOutSerde.configure(serdeConfig, false); // `false` for record values
        return avroOutSerde;
    }
}
