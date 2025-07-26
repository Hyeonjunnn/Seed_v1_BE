package com.hyeonjunnn.seed_v1_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SeedV1BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedV1BeApplication.class, args);
    }

}
