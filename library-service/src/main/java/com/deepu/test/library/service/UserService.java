package com.deepu.test.library.service;

import com.deepu.test.library.model.consume.User;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserService {
  private static final String USER_URL = "https://jsonplaceholder.org/users";

  public List<User> getUsers() {
    List<User> users =
        WebClient.create()
            .get()
            .uri(USER_URL)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
            .cache(Duration.ofMinutes(60))
            .retry(5)
            .block();
    users.stream()
        .forEach(user -> user.setFullName(user.getFirstname() + " " + user.getLastname()));
    return users;
  }
}
