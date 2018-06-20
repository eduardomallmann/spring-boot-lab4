package com.treinamento.microservices.springbootlab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootLab4Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLab4Application.class, args);
    }
}
