package io.github.syamantm.ktream.model.mapper;

import io.github.syamantm.avro.TransactionAvro;
import io.github.syamantm.ktream.model.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  TransactionAvro toAvro(TransactionRequest transactionRequest);
}
