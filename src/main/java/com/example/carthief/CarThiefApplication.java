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

    public static void main (String[] args) {
        SpringApplication.run(CarThiefApplication.class, args);
    }

    @Bean
    CommandLineRunner runOnStartUp(UserRepository repo, PasswordEncoder encoder){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                var user = repo.findByName("admin");
                if(user == null){
                    var u = new UserCredentials();
                    u.setName("admin");
                    u.setPassword(encoder.encode("admin"));
                    u.setRole(Role.ADMIN);
                    repo.save(u);
                }
            }
        };
    }

}
