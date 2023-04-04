package com.example.carthief.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        UserCredentials user = new UserCredentials(userDto.getName(), encoder.encode(userDto.getPassword()), userDto.getRole());

        if(userRepository.findByName(user.getName()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
