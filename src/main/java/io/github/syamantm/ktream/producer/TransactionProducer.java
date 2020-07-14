package io.github.syamantm.ktream.producer;

import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.TransactionRequest;
import io.github.syamantm.ktream.model.mapper.TransactionMapper;
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
public class TransactionProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProducer.class);

  @Autowired
  private ProcessBinding binding;

  public void send(TransactionRequest transactionRequest) {
    LOGGER.info("Received transaction id :{}, user id : {}, producing output",
        transactionRequest.getTransactionId(), transactionRequest.getUserId());

    TransactionAvro transactionAvro = TransactionMapper.INSTANCE.toAvro(transactionRequest);
    Message<TransactionAvro> message = MessageBuilder.withPayload(transactionAvro)
        .setHeader(KafkaHeaders.MESSAGE_KEY, transactionRequest.getUserId())
        .build();

    binding.transactionTopic().send(message);
  }
}
