package com.deepu.test.library.model.consume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
}
