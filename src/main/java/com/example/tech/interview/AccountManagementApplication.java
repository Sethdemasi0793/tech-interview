package com.example.tech.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class AccountManagementApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AccountManagementApplication.class, args);
    }

}
