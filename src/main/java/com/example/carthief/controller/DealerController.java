package com.example.carthief.controller;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import com.example.carthief.projection.DealerName;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerRepository dealerRepo;
    private final CarRepository carRepo;

    public DealerController(DealerRepository dealerRepository, CarRepository carRepository) {
        dealerRepo = dealerRepository;
        carRepo = carRepository;
    }

    @GetMapping
    List<Dealer> getDealers(){
        return dealerRepo.findAll();
    }

    @GetMapping("/{id}")
    Dealer getDealer(@PathVariable Long id){
        return dealerRepo.findById(id).orElseThrow();
    }

    @GetMapping("/names")
    List<DealerName> getName(){
        return dealerRepo.findNamesBy();
    }

    @PostMapping
    void addDealer(@RequestBody Dealer dealer){
        var copyOfCars = Set.copyOf(dealer.getCars());
        dealer.getCars().clear();
        dealer.getCars().addAll(carRepo.saveAll(copyOfCars));
        dealerRepo.save(dealer);
    }

    @PostMapping("/{dealerId}/cars")
    @Transactional
    public void addCarToDealer(@PathVariable Long dealerId, @RequestBody Car car){
        var savedCar = carRepo.save(car);
        var dealer = dealerRepo.findById(dealerId).orElseThrow();
        dealer.getCars().add(savedCar);
    }
}
