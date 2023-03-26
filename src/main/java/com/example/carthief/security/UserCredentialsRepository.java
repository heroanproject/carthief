package com.example.carthief.security;

import org.springframework.data.repository.ListCrudRepository;

public interface UserCredentialsRepository extends ListCrudRepository<UserCredentials, Long> {

    UserCredentials findByName(String name);
}
