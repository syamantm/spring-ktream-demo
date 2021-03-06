package io.github.syamantm.ktream.binding;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessBinding {

  String INPUT  = "input";
  String INPUT_INTERMEDIATE  = "intermediate";
  String OUTPUT = "output";

  @Output(INPUT)
  MessageChannel requestTopic();

  @Input(INPUT_INTERMEDIATE)
  KStream intermediate();

  @Output(OUTPUT)
  KStream responseTopic();
}
