package com.example.carthief;

import com.example.carthief.security.UserCredentials;
import com.example.carthief.security.UserCredentialsRepository;
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

    @Bean
    CommandLineRunner runOnStrartUp (UserCredentialsRepository repo, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run (String... args) throws Exception {
                var user = repo.findByName("admin");
                if (user == null) {
                    var u = new UserCredentials();
                    u.setName("admin");
                    u.setPassword(passwordEncoder.encode("password"));
                    repo.save(u);
                }
            }
        };

    }
}
