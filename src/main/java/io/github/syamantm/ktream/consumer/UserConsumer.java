package io.github.syamantm.ktream.consumer;

import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.avro.UserTransactionAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.mapper.UserTransactionMapper;
import java.util.HashMap;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(ProcessBinding.class)
public class UserConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

  @Autowired
  private Serde<UserAvro> valueSerde;

  @StreamListener
  public void processAggregation(
      @Input(ProcessBinding.USER_INPUT) KStream<String, UserAvro> userAvroKStream) {

    final StringSerde keySerDe = new StringSerde();
    userAvroKStream.groupByKey(Grouped.keySerde(keySerDe)).aggregate(HashMap::new, (key, value, map) -> {
      map.put(key, value); return map;
    }, Materialized.<String, Map<Foo, Bar>, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_NAME)
        .withKeySerde(keySerde).withValueSerde(valueMapSerde));
  }
}
