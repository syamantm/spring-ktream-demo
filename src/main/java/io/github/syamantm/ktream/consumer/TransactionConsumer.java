package io.github.syamantm.ktream.consumer;

import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.avro.UserTransactionAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.mapper.UserTransactionMapper;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ProcessBinding.class)
public class TransactionConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConsumer.class);

  @StreamListener
  @SendTo(ProcessBinding.OUTPUT)
  public KStream<String, UserTransactionAvro> processAggregation(
      @Input(ProcessBinding.TRANSACTION_INTERMEDIATE) KStream<String, TransactionAvro> transactions,
      @Input(ProcessBinding.ALL_USER) KTable<String, UserAvro> userTable) {

    return transactions.leftJoin(userTable,
        (transaction, user) ->
            UserTransactionMapper.INSTANCE.toAvro(user, transaction));
  }
}
