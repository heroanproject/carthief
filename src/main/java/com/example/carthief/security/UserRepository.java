package com.example.carthief.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserCredentials, Long> {
    UserCredentials findByName(String name);
}
