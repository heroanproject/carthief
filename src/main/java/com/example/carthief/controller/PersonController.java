package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Person;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepo;
    private final CarRepository carRepo;


    public PersonController (PersonRepository personRepository, CarRepository carRepository) {

        personRepo = personRepository;
        carRepo = carRepository;
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

    @PostMapping("/{personId}/cars")
    @Transactional
    public void addPersonToCar(@PathVariable Long personId, @RequestBody Car car) {
        var savedCar = carRepo.save(car);
        var person = personRepo.findById(personId).orElseThrow();
        person.setCar(savedCar);
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

    @DeleteMapping("/{personId}/cars/{carId}")
    @Transactional
    public void deleteCarFromPerson(@PathVariable Long personId, @PathVariable Long carId) {
        var person = personRepo.findById(personId).orElseThrow();
        if (person.getCar() != null && person.getCar().getId().equals(carId)) {
            person.setCar(null);

        }
    }
}
