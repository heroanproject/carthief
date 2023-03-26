package com.example.carthief;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CarThiefApplication {

    public static void main (String[] args) {
        SpringApplication.run(CarThiefApplication.class, args);
    }


}
