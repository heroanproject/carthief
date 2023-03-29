package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarRepository carRepo;

    public CarController(CarRepository carRepository) {
        carRepo = carRepository;
    }
        @GetMapping("/{id}")
        Car getACar(@PathVariable long id) {
            return carRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
    @GetMapping
    List<Car> getCars() {
        return carRepo.findAll();
    }
    @PostMapping
    void addCar(@RequestBody Car car) {
        String name = car.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        carRepo.save(car);
    }
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return carRepo.save(car);
    }
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepo.deleteById(id);
    }
}
