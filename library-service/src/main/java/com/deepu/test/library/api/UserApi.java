package com.deepu.test.library.api;

import com.deepu.test.library.model.consume.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/library/user/")
public interface UserApi {
  @Operation(summary = "Get all Borrowers", description = "Get all the Borrowers from the DB")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
  @GetMapping(value = "/list", produces = "application/json")
  List<User> findAll();
}
