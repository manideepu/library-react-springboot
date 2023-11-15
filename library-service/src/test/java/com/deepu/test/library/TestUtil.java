package com.deepu.test.library;

import static java.time.ZoneOffset.UTC;
import static org.mockito.Mockito.mockStatic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Clock;
import java.time.LocalDateTime;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

public class TestUtil {

  public static Answer<Object> firstArgument = i -> i.getArgument(0);

  public static String toJson(Object object) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    return mapper.writeValueAsString(object);
  }

  public static void mockClockAsAt(LocalDateTime dateTime, Runnable runnable) {
    var clock = Clock.fixed(dateTime.toInstant(UTC), UTC);
    try (MockedStatic<Clock> mocked = mockStatic(Clock.class)) {
      mocked.when(Clock::systemDefaultZone).thenReturn(clock);
      runnable.run();
    }
  }
}
