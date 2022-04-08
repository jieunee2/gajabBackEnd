package com.gajob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GajabBackEndApplication {

  public static void main(String[] args) {
    SpringApplication.run(GajabBackEndApplication.class, args);
  }

}
