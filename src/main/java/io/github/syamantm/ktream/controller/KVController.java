package io.github.syamantm.ktream.controller;

import io.github.syamantm.ktream.model.KeyValueData;
import io.github.syamantm.ktream.producer.KVProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kv-stream")
public class KVController {

  @Autowired
  private KVProducer producer;

  @PostMapping("/send")
  public ResponseEntity<String> calculateChain(@RequestBody KeyValueData request) {

    producer.send(request);
    return ResponseEntity.accepted()
        .body("sent request to kafka");
  }
}
