package com.deepu.test.library.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "author")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "author",
      cascade = {CascadeType.ALL})
  List<Book> books = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id", nullable = false)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
}
