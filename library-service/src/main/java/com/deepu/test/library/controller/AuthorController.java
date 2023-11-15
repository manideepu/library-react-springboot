package com.deepu.test.library.controller;

import com.deepu.test.library.api.AuthorApi;
import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.model.AuthorSaveRequest;
import com.deepu.test.library.service.AuthorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthorController implements AuthorApi {

  private final AuthorService authorService;

  @Override
  public List<AuthorDTO> findAll() {
    return authorService.findAll();
  }

  @Override
  public AuthorDTO save(AuthorSaveRequest author) {
    return authorService.save(author);
  }
}
