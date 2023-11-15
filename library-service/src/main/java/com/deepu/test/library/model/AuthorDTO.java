package com.deepu.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String fullName;
  private String email;
}
