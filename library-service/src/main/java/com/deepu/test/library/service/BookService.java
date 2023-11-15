package com.deepu.test.library.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.deepu.test.library.entity.Author;
import com.deepu.test.library.entity.Book;
import com.deepu.test.library.mapper.BookMapper;
import com.deepu.test.library.model.BookDTO;
import com.deepu.test.library.model.BookSaveRequest;
import com.deepu.test.library.model.consume.User;
import com.deepu.test.library.repository.AuthorRepository;
import com.deepu.test.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final UserService userService;
  private final BookMapper bookMapper;

  public List<BookDTO> findAll() {
    List<BookDTO> books =
        bookRepository.findAll().stream()
            .map(book -> bookMapper.toBookDTO(book))
            .collect(Collectors.toList());
    populateUser(books);
    return books;
  }

  private Map<Long, User> getUserMap() {
    return userService.getUsers().stream()
        .collect(Collectors.toMap(User::getId, Function.identity()));
  }

  private void populateUser(List<BookDTO> books) {
    Map<Long, User> userMap = getUserMap();
    books.stream()
        .filter(
            book ->
                book.isBorrowed()
                    && nonNull(book.getBorrowerId())
                    && nonNull(userMap.get(book.getBorrowerId())))
        .forEach(book -> book.setBorrower(userMap.get(book.getBorrowerId())));
  }

  public List<BookDTO> findAllSortedByName() {
    List<BookDTO> books =
        bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title")).stream()
            .map(book -> bookMapper.toBookDTO(book))
            .collect(Collectors.toList());
    populateUser(books);
    return books;
  }

  public BookDTO findById(Long id) {
    BookDTO bookDTO = bookMapper.toBookDTO(bookRepository.findById(id).orElse(null));
    if (isNull(bookDTO)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }
    return bookDTO;
  }

  public BookDTO save(BookSaveRequest bookSaveRequest) {
    Book toSave = bookMapper.fromBookSaveRequestToBook(bookSaveRequest);
    if (nonNull(bookSaveRequest) && nonNull(bookSaveRequest.getAuthorId())) {
      Author author = authorRepository.findById(bookSaveRequest.getAuthorId()).orElse(null);
      if (nonNull(author)) {
        toSave.setAuthor(author);
        Book saved = bookRepository.save(toSave);
        return bookMapper.toBookDTO(saved);
      }
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "entity not found");
  }

  public BookDTO update(Long bookId, BookSaveRequest bookSaveRequest) {
    if (nonNull(bookSaveRequest)) {
      Book toUpdate = bookRepository.findById(bookId).orElse(null);
      if (nonNull(toUpdate)) {
        Author author = authorRepository.findById(bookSaveRequest.getAuthorId()).orElse(null);
        if (nonNull(author)) {
          toUpdate.setAuthor(author);
          toUpdate.setTitle(bookSaveRequest.getTitle());
          toUpdate.setPublisher(bookSaveRequest.getPublisher());
          Book saved = bookRepository.save(toUpdate);
          return bookMapper.toBookDTO(saved);
        }
      }
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "entity not found");
  }

  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }

  @Transactional
  public BookDTO borrowBook(Long bookId, Long userId) {
    Book book = bookRepository.findById(bookId).orElse(null);
    if (nonNull(book) && !book.isBorrowed()) {
      Map<Long, User> userMap = getUserMap();
      if (nonNull(userMap.get(userId))) {
        book.setBorrowerId(userId);
        book.setBorrowed(true);
        Book saved = bookRepository.save(book);
        return bookMapper.toBookDTO(saved);
      }
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input");
  }

  @Transactional
  public BookDTO returnBook(Long bookId, Long userId) {
    Book book = bookRepository.findById(bookId).orElse(null);
    if (nonNull(book) && book.isBorrowed() && userId.equals(book.getBorrowerId())) {
      book.setBorrowerId(null);
      book.setBorrowed(false);
      Book saved = bookRepository.save(book);
      return bookMapper.toBookDTO(saved);
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input");
  }
}
