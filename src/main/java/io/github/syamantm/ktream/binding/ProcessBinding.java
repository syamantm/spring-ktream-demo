package io.github.syamantm.ktream.binding;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessBinding {

  String USER_INPUT  = "user-input";
  String ALL_USER  = "all-users";
  String TRANSACTION_INPUT  = "transaction-input";
  String TRANSACTION_INTERMEDIATE  = "transaction-intermediate";
  String OUTPUT  = "output";

  @Output(USER_INPUT)
  MessageChannel userTopic();

  @Output(TRANSACTION_INPUT)
  MessageChannel transactionTopic();

  @Input(TRANSACTION_INTERMEDIATE)
  KStream intermediate();

  @Output(OUTPUT)
  KStream output();

  @Input(ALL_USER)
  KTable allUsers();

}
