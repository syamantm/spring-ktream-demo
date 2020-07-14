package io.github.syamantm.ktream.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRequest {
  String userId;
  String transactionId;
  long timestamp;
  BigDecimal amount;
}
