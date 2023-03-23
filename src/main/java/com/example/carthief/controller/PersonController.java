package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Person;
import com.example.carthief.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepo;

    public PersonController (PersonRepository personRepository) {

        personRepo = personRepository;
    }

    @GetMapping("/{id}")
    Person getName (@PathVariable long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    List<Person> getName () {
        return personRepo.findAll();
    }

    @PostMapping
    void addNumber(@RequestBody Person person) {
        String name = person.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        personRepo.save(person);
    }
    @PutMapping("/{id}")
    public Person updateCar(@PathVariable Long id, @RequestBody Person person){
        person.setId(id);
        return personRepo.save(person);
    }
    @DeleteMapping("/{id}")
    void deleteOrg(@PathVariable Long id){
        personRepo.deleteById(id);
    }
}
