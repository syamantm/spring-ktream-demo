package io.github.syamantm.ktream.producer;

import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.KeyValueData;
import io.github.syamantm.avro.KeyValueInputAvro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ProcessBinding.class)
public class KVProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KVProducer.class);

  @Autowired
  private ProcessBinding binding;

  public void send(KeyValueData data) {
    LOGGER.info("Received data with key : {}, producing output", data.getKey());
    KeyValueInputAvro avroData = KeyValueInputAvro.newBuilder()
        .setKey(data.getKey())
        .setValue(data.getValue())
        .build();
    Message<KeyValueInputAvro> message = MessageBuilder.withPayload(avroData)
        .setHeader(KafkaHeaders.MESSAGE_KEY, data.getKey())
        .build();

    binding.requestTopic().send(message);
  }

}
