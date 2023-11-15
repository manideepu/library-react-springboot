package com.deepu.test.library.controller;

import com.deepu.test.library.api.UserApi;
import com.deepu.test.library.model.consume.User;
import com.deepu.test.library.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
  private final UserService userService;

  @Override
  public List<User> findAll() {
    return userService.getUsers();
  }
}
