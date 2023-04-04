package com.example.carthief.controller;

import com.example.carthief.dto.DealerDto;
import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import com.example.carthief.mapper.Mapper;
import com.example.carthief.projection.DealerName;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {

    private final DealerRepository dealerRepo;
    private final CarRepository carRepo;
    private final Mapper mapper;

    public DealerController(DealerRepository dealerRepository, CarRepository carRepository, Mapper mapper) {
        dealerRepo = dealerRepository;
        carRepo = carRepository;
        this.mapper = mapper;
    }

    @GetMapping
    List<DealerDto> getDealers(){
        return mapper.mapDealerToDto(dealerRepo.findAll());
    }

    @GetMapping("/{dealerId}")
    DealerDto getDealer(@PathVariable Long dealerId){
        return mapper.mapDealerToDto(dealerRepo.findById(dealerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/names")
    List<DealerName> getName(){
        return dealerRepo.findNamesBy();
    }

    @PostMapping
    void addDealer(@RequestBody DealerDto dealerDto){
        Set<Car> copyOfCars;
        if(dealerDto.getCars() != null) {
            copyOfCars = Set.copyOf(dealerDto.getCars());
            dealerDto.getCars().clear();
            dealerDto.getCars().addAll(carRepo.saveAll(copyOfCars));
        }
        dealerRepo.save(mapper.mapDtoToDealer(dealerDto));
    }

    @DeleteMapping("/{dealerId}")
    void deleteOrg(@PathVariable Long dealerId) {
        dealerRepo.deleteById(dealerId);
    }

    @PostMapping("/{dealerId}/cars")
    @Transactional
    public void addCarToDealer(@PathVariable Long dealerId, @RequestBody Car car){
        var savedCar = carRepo.save(car);
        var dealer = dealerRepo.findById(dealerId).orElseThrow();
        dealer.getCars().add(savedCar);
    }

    @DeleteMapping("/{dealerId}/cars/{carId}")
    @Transactional
    public void removeCarFromDealer(@PathVariable Long dealerId, @PathVariable Long carId){
        Dealer dealer = dealerRepo.findById(dealerId).orElseThrow();
        dealer.getCars().remove(carRepo.findById(carId).orElseThrow());
        dealerRepo.save(dealer);
    }
}
