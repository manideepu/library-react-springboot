package com.deepu.test.library.model;

import com.deepu.test.library.model.consume.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
  private Long id;
  private String title;
  private String publisher;
  private boolean borrowed;
  private Long borrowerId;
  private AuthorDTO author;
  private User borrower;
}
