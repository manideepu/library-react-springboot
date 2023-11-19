package com.deepu.test.library.model.consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullName;
  private String email;
  private Address address;
  private String birthDate;
  private String phone;
}
