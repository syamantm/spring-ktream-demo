package io.github.syamantm.ktream.model.mapper;

import io.github.syamantm.avro.UserAvro;
import io.github.syamantm.ktream.model.UserRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

  UserAvro toAvro(UserRequest userRequest);

  @InheritInverseConfiguration
  UserRequest fromAvro(UserAvro userAvro);
}
