package com.deepu.test.library.mapper;

import com.deepu.test.library.entity.Book;
import com.deepu.test.library.model.BookDTO;
import com.deepu.test.library.model.BookSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
  BookDTO toBookDTO(Book book);

  Book toBook(BookDTO bookDTO);

  Book fromBookSaveRequestToBook(BookSaveRequest bookSaveRequest);
}
