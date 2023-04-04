package com.example.carthief;


import com.example.carthief.security.Role;
import com.example.carthief.security.UserCredentials;
import com.example.carthief.security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CarThiefApplication {

    public static final String INPUT = "admin";

    public static void main (String[] args) {
        SpringApplication.run(CarThiefApplication.class, args);
    }

    @Bean
    CommandLineRunner runOnStartUp(UserRepository repo, PasswordEncoder encoder){
        return args -> {
            var user = repo.findByName(INPUT);
            if(user == null){
                var u = new UserCredentials(INPUT, encoder.encode(INPUT), Role.ADMIN);
                repo.save(u);
            }
        };
    }

}
