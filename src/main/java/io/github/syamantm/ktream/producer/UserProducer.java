package io.github.syamantm.ktream.producer;

import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.UserRequest;
import io.github.syamantm.ktream.model.mapper.UserMapper;
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
public class UserProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserProducer.class);

  @Autowired
  private ProcessBinding binding;

  public void send(UserRequest userRequest) {
    LOGGER.info("Received user with id : {}, producing output", userRequest.getUserId());
    UserAvro userAvro = UserMapper.INSTANCE.toAvro(userRequest);
    Message<UserAvro> message = MessageBuilder.withPayload(userAvro)
        .setHeader(KafkaHeaders.MESSAGE_KEY, userRequest.getUserId())
        .build();

    binding.userTopic().send(message);
  }
}
