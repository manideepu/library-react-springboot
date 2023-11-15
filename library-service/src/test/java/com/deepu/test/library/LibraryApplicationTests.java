package com.deepu.test.library;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.deepu.test.library.controller.AuthorController;
import com.deepu.test.library.controller.BookController;
import com.deepu.test.library.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests {
  @Autowired private BookController bookController;
  @Autowired private UserController userController;
  @Autowired private AuthorController authorController;

  @Test
  void contextLoads() {
    assertThat(bookController).isNotNull();
    assertThat(userController).isNotNull();
    assertThat(authorController).isNotNull();
  }
}
