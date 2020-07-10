package io.github.syamantm.ktream.consumer;

import io.github.syamantm.avro.KeyValueInputAvro;
import io.github.syamantm.avro.KeyValueOutputAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
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
  public KeyValueOutputAvro processAggregation(KeyValueInputAvro request) {
    LOGGER.info("Received request with key : {}", request.getKey());
    return KeyValueOutputAvro.newBuilder()
        .setKey(request.getKey())
        .setValue(request.getValue())
        .setJoinedField("TODO")
        .build();
  }
}
