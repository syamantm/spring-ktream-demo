package io.github.syamantm.ktream.controller;

import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.ktream.binding.ProcessBinding;
import io.github.syamantm.ktream.model.UserRequest;
import io.github.syamantm.ktream.model.mapper.UserMapper;
import io.github.syamantm.ktream.producer.UserProducer;
import java.util.Optional;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserProducer producer;

  @Autowired
  private InteractiveQueryService interactiveQueryService;

  @PostMapping
  public ResponseEntity<String> create(@RequestBody UserRequest request) {

    producer.send(request);
    return ResponseEntity.accepted()
        .body("sent request to kafka");
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserRequest> getById(@PathVariable String userId) {

    final ReadOnlyKeyValueStore<String, UserAvro> topFiveStore =
        interactiveQueryService.getQueryableStore(ProcessBinding.ALL_USER, QueryableStoreTypes.keyValueStore());

    return Optional.ofNullable(topFiveStore.get(userId))
        .map(UserMapper.INSTANCE::fromAvro)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
