package io.github.syamantm.ktream.controller;

import io.github.syamantm.ktream.model.TransactionRequest;
import io.github.syamantm.ktream.producer.TransactionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  private TransactionProducer producer;

  @PostMapping("/send")
  public ResponseEntity<String> create(@RequestBody TransactionRequest request) {

    producer.send(request);
    return ResponseEntity.accepted()
        .body("sent request to kafka");
  }
}
