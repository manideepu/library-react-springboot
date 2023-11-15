package com.deepu.test.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.YesNoConverter;

@Table(name = "book")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id", nullable = false)
  private Long id;

  private String title;
  private String publisher;

  @Convert(converter = YesNoConverter.class)
  private boolean borrowed;

  private Long borrowerId;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;
}
