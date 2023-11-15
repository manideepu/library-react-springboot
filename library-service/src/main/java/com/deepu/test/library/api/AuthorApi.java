package com.deepu.test.library.api;

import com.deepu.test.library.model.AuthorDTO;
import com.deepu.test.library.model.AuthorSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/v1/library/author")
public interface AuthorApi {

  @Operation(summary = "Get all Authors", description = "Get all the Authors from the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(value = "/list", produces = "application/json")
  List<AuthorDTO> findAll();

  @Operation(summary = "Save an Author", description = "Save an Author in DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  AuthorDTO save(@RequestBody @Valid @NotNull AuthorSaveRequest author);
}
