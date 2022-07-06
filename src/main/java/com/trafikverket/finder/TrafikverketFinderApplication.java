package com.trafikverket.finder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@SpringBootApplication
@EnableScheduling
public class TrafikverketFinderApplication {

  public static void main(String[] args) {
    SpringApplication.run(TrafikverketFinderApplication.class, args);
  }

  @Bean
  public ObjectMapper objectMapper() {
    SimpleFilterProvider filters = new SimpleFilterProvider();
    filters.setFailOnUnknownId(false);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setFilterProvider(filters);
    objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    objectMapper.registerModule(new JavaTimeModule());

    return objectMapper;
  }
}
