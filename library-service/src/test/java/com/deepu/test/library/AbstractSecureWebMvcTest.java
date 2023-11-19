package com.deepu.test.library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
  AopAutoConfiguration.class,
})
public abstract class AbstractSecureWebMvcTest extends AbstractWebMvcTest {
  @BeforeEach
  public void setUp() {
    System.setProperty("bypass", "disabled");
  }

  @AfterEach
  public void destroy() {
    System.setProperty("bypass", "enabled");
  }
}
