package io.github.syamantm.ktream.model.mapper;

import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.avro.UserTransactionAvro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserTransactionMapper {

  UserTransactionMapper INSTANCE = Mappers.getMapper(UserTransactionMapper.class);

  @Mapping(source = "user.userId", target = "userId")
  @Mapping(source = "user.firstName", target = "firstName")
  @Mapping(source = "user.lastName", target = "lastName")
  @Mapping(source = "user.location", target = "location")
  @Mapping(source = "transaction.transactionId", target = "transactionId")
  @Mapping(source = "transaction.timestamp", target = "timestamp")
  @Mapping(source = "transaction.amount", target = "amount")
  UserTransactionAvro toAvro(UserAvro user, TransactionAvro transaction);
}
