package com.deepu.test.library.service;

import com.deepu.test.library.entity.Author;
import com.deepu.test.library.mapper.AuthorMapper;
import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.model.AuthorSaveRequest;
import com.deepu.test.library.repository.AuthorRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;
  private final AuthorMapper authorMapper;

  public List<AuthorDTO> findAll() {
    return authorRepository.findAll().stream()
        .map(author -> authorMapper.toAuthorDTO(author))
        .collect(Collectors.toList());
  }

  public AuthorDTO save(AuthorSaveRequest authorSaveRequest) {
    Author author = authorMapper.fromAuthorSaveRequestToAuthor(authorSaveRequest);
    Author saved = authorRepository.save(author);
    return authorMapper.toAuthorDTO(saved);
  }

  public AuthorDTO findById(Long authorId) {
    Author author = authorRepository.findById(authorId).orElse(null);
    return authorMapper.toAuthorDTO(author);
  }
}
