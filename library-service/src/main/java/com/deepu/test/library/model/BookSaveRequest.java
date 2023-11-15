package com.deepu.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSaveRequest {
  private String title;
  private String publisher;
  private Long authorId;
}
