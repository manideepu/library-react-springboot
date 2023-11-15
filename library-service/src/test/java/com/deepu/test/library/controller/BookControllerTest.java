package com.deepu.test.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deepu.test.library.AbstractSecureWebMvcTest;
import com.deepu.test.library.model.BookDTO;
import com.deepu.test.library.service.BookService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest extends AbstractSecureWebMvcTest {
  @MockBean BookService bookService;
  private static final String LIST_BOOKS = "/v1/library/book/list";

  @Test
  public void testGetFindAll() throws Exception {
    when(bookService.findAll())
        .thenReturn(List.of(BookDTO.builder().id(1L).title("My First Book").build()));
    mockMvc
        .perform(get(LIST_BOOKS))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(1))
        .andExpect(jsonPath("$.[0].title").value("My First Book"));
  }
}
