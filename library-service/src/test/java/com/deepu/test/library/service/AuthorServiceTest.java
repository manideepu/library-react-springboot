package com.deepu.test.library.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.deepu.test.library.entity.Author;
import com.deepu.test.library.mapper.AuthorMapper;
import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.model.AuthorSaveRequest;
import com.deepu.test.library.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
  @Mock private AuthorRepository authorRepository;
  @Mock private AuthorMapper authorMapper;
  @InjectMocks private AuthorService authorService;

  @Test
  public void testFindAll() {
    when(authorRepository.findAll())
        .thenReturn(
            List.of(Author.builder().id(1L).firstName("William").lastName("Shakespeare").build()));
    List<AuthorDTO> authorDTOS = authorService.findAll();
    assertThat(authorDTOS).isNotNull();
    assertThat(authorDTOS.size()).isEqualTo(1);
  }

  @Test
  public void testFindById() {
    when(authorMapper.toAuthorDTO(any()))
        .thenReturn(
            AuthorDTO.builder()
                .id(1L)
                .firstName("William")
                .lastName("Shakespeare")
                .email("William.Shakespeare@revenue.nsw.gov.au")
                .build());
    when(authorRepository.findById(1L))
        .thenReturn(
            Optional.of(
                Author.builder().id(1L).firstName("William").lastName("Shakespeare").build()));
    AuthorDTO authorDTO = authorService.findById(1L);
    assertThat(authorDTO).isNotNull();

    assertThat(authorDTO.getFirstName()).isEqualTo("William");
    assertThat(authorDTO.getLastName()).isEqualTo("Shakespeare");
    assertThat(authorDTO.getEmail()).isEqualTo("William.Shakespeare@revenue.nsw.gov.au");
  }

  @Test
  public void testSave() {
    AuthorSaveRequest authorSaveRequest =
        AuthorSaveRequest.builder()
            .firstName("Mark")
            .lastName("Twain")
            .email("Mark.Twain@revenue.nsw.gov.au")
            .build();
    Author authorToSave =
        Author.builder()
            .firstName("Mark")
            .lastName("Twain")
            .email("Mark.Twain@revenue.nsw.gov.au")
            .build();
    Author authorSaved =
        Author.builder()
            .id(6L)
            .firstName("Mark")
            .lastName("Twain")
            .email("Mark.Twain@revenue.nsw.gov.au")
            .build();
    when(authorMapper.fromAuthorSaveRequestToAuthor(any())).thenReturn(authorToSave);
    when(authorRepository.save(any())).thenReturn(authorSaved);
    when(authorMapper.toAuthorDTO(authorSaved))
        .thenReturn(
            AuthorDTO.builder()
                .id(6L)
                .firstName("Mark")
                .lastName("Twain")
                .email("Mark.Twain@revenue.nsw.gov.au")
                .build());

    AuthorDTO authorDTO = authorService.save(authorSaveRequest);

    assertThat(authorDTO).isNotNull();
    assertThat(authorDTO.getId()).isEqualTo(6L);
    assertThat(authorDTO.getFirstName()).isEqualTo("Mark");
    assertThat(authorDTO.getLastName()).isEqualTo("Twain");
    assertThat(authorDTO.getEmail()).isEqualTo("Mark.Twain@revenue.nsw.gov.au");
  }
}
