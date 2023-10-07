package ru.venikov.spring.boot_security;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.venikov.spring.boot_security.configure.SecurityConfig;

import java.io.IOException;

@SpringBootApplication
public class BootSecurityApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(BootSecurityApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
