package com.deepu.test.library.controller;

import com.deepu.test.library.api.BookApi;
import com.deepu.test.library.model.BookDTO;
import com.deepu.test.library.model.BookSaveRequest;
import com.deepu.test.library.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {
  private final BookService bookService;

  @Override
  public List<BookDTO> findAll() {
    return bookService.findAll();
  }

  @Override
  public List<BookDTO> findAllSortedByName() {
    return bookService.findAllSortedByName();
  }

  @Override
  public BookDTO findById(Long bookId) {
    return bookService.findById(bookId);
  }

  @Override
  public BookDTO save(BookSaveRequest book) {
    return bookService.save(book);
  }

  @Override
  public BookDTO update(Long bookId, BookSaveRequest book) {
    return bookService.update(bookId, book);
  }

  @Override
  public void deleteById(Long bookId) {
    bookService.deleteById(bookId);
  }

  @Override
  public BookDTO borrowBook(Long bookId, Long userId) {
    return bookService.borrowBook(bookId, userId);
  }

  @Override
  public BookDTO returnBook(Long bookId, Long userId) {
    return bookService.returnBook(bookId, userId);
  }
}
