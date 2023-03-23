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
@RequestMapping("/person")
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

    }

//    @PostMapping("/{dealerId}/cars")
//    @Transactional
//    public void addCarToDealer(@PathVariable Long dealerId, @RequestBody Car car){
//        var savedCar = carRepo.save(car);
//        var dealer = dealerRepo.findById(dealerId).orElseThrow();
//        dealer.getCars().add(savedCar);
//    }
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
