package com.deepu.test.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deepu.test.library.AbstractSecureWebMvcTest;
import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.service.AuthorService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest extends AbstractSecureWebMvcTest {
  @MockBean AuthorService authorService;
  private static final String LIST_BOOKS = "/v1/library/author/list";

  @Test
  public void testGetFindAll() throws Exception {
    when(authorService.findAll())
        .thenReturn(
            List.of(
                AuthorDTO.builder().id(1L).firstName("William").lastName("Shakespeare").build()));

    mockMvc
        .perform(get(LIST_BOOKS))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(1))
        .andExpect(jsonPath("$.[0].firstName").value("William"));
  }
}
