package com.example.carthief.controller;

import com.example.carthief.entity.Dealer;
import com.example.carthief.projection.DealerName;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import com.example.carthief.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private DealerRepository dealerRepo;
    private CarRepository carRepo;
    private PersonRepository personRepo;

    public DealerController(DealerRepository dealerRepository, CarRepository carRepository, PersonRepository personRepository) {
        dealerRepo = dealerRepository;
        carRepo = carRepository;
        personRepo = personRepository;
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
}
