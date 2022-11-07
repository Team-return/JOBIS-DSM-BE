package com.example.jobis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobisApplication.class, args);
    }

}
