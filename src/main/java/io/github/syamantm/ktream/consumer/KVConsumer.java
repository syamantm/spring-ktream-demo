package io.github.syamantm.ktream.consumer;

import io.github.syamantm.avro.KeyValueInputAvro;
import io.github.syamantm.avro.KeyValueOutputAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Serialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ProcessBinding.class)
public class KVConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KVConsumer.class);

  @StreamListener(ProcessBinding.INPUT_INTERMEDIATE)
  @SendTo(ProcessBinding.OUTPUT)
  public KStream<String, KeyValueOutputAvro> processAggregation(KStream<String, KeyValueInputAvro> stream) {
    return stream.map((key, value) -> new KeyValue<>(key, KeyValueOutputAvro.newBuilder()
        .setKey(key)
        .setValue(value.getValue())
        .setJoinedField("TODO")
        .build()));
  }
}
