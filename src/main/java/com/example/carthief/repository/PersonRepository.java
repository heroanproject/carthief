package com.example.carthief.repository;

import com.example.carthief.entity.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person,Long> {
}
