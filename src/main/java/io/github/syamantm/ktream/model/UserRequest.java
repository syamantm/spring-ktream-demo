package io.github.syamantm.ktream.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
  String userId;
  String firstName;
  String lastName;
  String location;
}
