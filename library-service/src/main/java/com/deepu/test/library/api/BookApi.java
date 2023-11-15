package com.deepu.test.library.api;

import com.deepu.test.library.model.BookDTO;
import com.deepu.test.library.model.BookSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/v1/library/book")
public interface BookApi {

  @Operation(summary = "Get all Books", description = "Get all the Books from the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(value = "/list", produces = "application/json")
  List<BookDTO> findAll();

  @Operation(
      summary = "Get all Books order by name",
      description = "Get all the Books from the DB order by title")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(value = "/sortByTitle", produces = "application/json")
  List<BookDTO> findAllSortedByName();

  @Operation(summary = "Get a Book", description = "Get a Book by Id from the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(value = "/{bookId}", produces = "application/json")
  BookDTO findById(
      @Parameter(example = "99")
          @PathVariable("bookId")
          @NotNull
          @Min(value = 1L, message = "bookId Id must be greater than 1")
          @Max(value = 999999999L, message = "bookId Id must not be greater than 999999999L")
          Long bookId);

  @Operation(summary = "Save a Book", description = "Save a Book to the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  BookDTO save(@Valid @RequestBody BookSaveRequest book);

  @Operation(summary = "Update a Book", description = "Update a Book by Id from the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @PutMapping(value = "/{bookId}", produces = "application/json")
  BookDTO update(
      @Parameter(example = "99")
          @PathVariable("bookId")
          @NotNull
          @Min(value = 1L, message = "bookId Id must be greater than 1")
          @Max(value = 999999999L, message = "bookId Id must not be greater than 999999999L")
          Long bookId,
      @Valid @RequestBody BookSaveRequest book);

  @Operation(summary = "Delete a Book", description = "Delete a Book from DB by bookId")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @DeleteMapping(value = "/{bookId}")
  void deleteById(
      @Parameter(example = "99")
          @PathVariable("bookId")
          @NotNull
          @Min(value = 1L, message = "bookId Id must be greater than 1")
          @Max(value = 999999999L, message = "bookId Id must not be greater than 999999999L")
          Long bookId);

  @Operation(summary = "Borrow a Book", description = "Borrow a Book")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @PutMapping(value = "/borrow/{bookId}/{userId}")
  BookDTO borrowBook(
      @Parameter(example = "99")
          @PathVariable("bookId")
          @NotNull
          @Min(value = 1L, message = "bookId Id must be greater than 1")
          @Max(value = 999999999L, message = "bookId Id must not be greater than 999999999L")
          Long bookId,
      @Parameter(example = "99")
          @PathVariable("userId")
          @NotNull
          @Min(value = 1L, message = "userId Id must be greater than 1")
          @Max(value = 999999999L, message = "userId Id must not be greater than 999999999L")
          Long userId);

  @Operation(summary = "Return a Book", description = "Return a Book")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @PutMapping(value = "/return/{bookId}/{userId}")
  BookDTO returnBook(
      @Parameter(example = "99")
          @PathVariable("bookId")
          @NotNull
          @Min(value = 1L, message = "bookId Id must be greater than 1")
          @Max(value = 999999999L, message = "bookId Id must not be greater than 999999999L")
          Long bookId,
      @Parameter(example = "99")
          @PathVariable("userId")
          @NotNull
          @Min(value = 1L, message = "userId Id must be greater than 1")
          @Max(value = 999999999L, message = "userId Id must not be greater than 999999999L")
          Long userId);
}
