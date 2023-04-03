package com.example.carthief.controller;

import com.example.carthief.dto.CarDto;
import com.example.carthief.mapper.Mapper;
import com.example.carthief.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepo;
    private final Mapper mapper;

    public CarController(CarRepository carRepository, Mapper mapper) {
        carRepo = carRepository;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    CarDto getACar(@PathVariable long id) {
        return mapper.mapCarToDto(carRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    List<CarDto> getCars() {
        return mapper.mapCarToDto(carRepo.findAll());
    }

    @PostMapping
    void addCar(@RequestBody CarDto carDto) {
        String name = carDto.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        carRepo.save(mapper.mapDtoToCar(carDto));
    }

    @PutMapping("/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        carDto.setId(id);
        return mapper.mapCarToDto(carRepo.save(mapper.mapDtoToCar(carDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteCar (@PathVariable Long id) {
        carRepo.deleteById(id);
    }
}
