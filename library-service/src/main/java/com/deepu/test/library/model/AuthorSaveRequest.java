package com.deepu.test.library.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorSaveRequest {
  @NotBlank(message = "firstName must not be null or empty")
  @Size(max = 256)
  private String firstName;

  @NotBlank(message = "lastName must not be null or empty")
  @Size(max = 256)
  private String lastName;

  @Email private String email;
}
