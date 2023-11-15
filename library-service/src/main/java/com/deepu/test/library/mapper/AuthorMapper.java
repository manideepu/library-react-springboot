package com.deepu.test.library.mapper;

import static java.util.Objects.isNull;

import com.deepu.test.library.entity.Author;
import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.model.AuthorSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {
  Author toAuthor(AuthorDTO authorDTO);

  Author fromAuthorSaveRequestToAuthor(AuthorSaveRequest authorSaveRequest);

  @Mapping(target = "fullName", expression = "java(getFullName(author))")
  AuthorDTO toAuthorDTO(Author author);

  default String getFullName(Author author) {
    if (isNull(author)) {
      return null;
    }
    return author.getFirstName() + " " + author.getLastName();
  }
}
