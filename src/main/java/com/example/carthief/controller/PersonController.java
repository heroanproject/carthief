package com.example.carthief.controller;

import com.example.carthief.entity.Person;
import com.example.carthief.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repo;

    public PersonController (PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    Person getName (@PathVariable long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping
    List<Person> getName () {
        return repo.findAll();
    }

    @PostMapping
    void addName (@RequestBody Person person) {
        String name = person.getName();
        if (name == null || name.isEmpty()) throw new IllegalStateException();
        repo.save(person);
    }


}
