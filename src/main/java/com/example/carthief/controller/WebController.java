package com.example.carthief.controller;

import com.example.carthief.entity.Dealer;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import com.example.carthief.repository.PersonRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    private final DealerRepository dealerRepository;
    private final CarRepository carRepository;
    private final PersonRepository personRepository;

    public WebController(DealerRepository dealerRepository, CarRepository carRepository, PersonRepository personRepository) {
        this.dealerRepository = dealerRepository;
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/carthief")
    String dealers(Model model){
        model.addAttribute("allDealers", dealerRepository.findAll());
        model.addAttribute("allCars", carRepository.findAll());
        model.addAttribute("allPersons", personRepository.findAll());
        return "carThief";
    }

    @GetMapping("/search")
    String searchByCarName(Model model, @Param("keyword") String keyword){
        List<Dealer> list = dealerRepository.findByCarsName(keyword);
        model.addAttribute("listDealers", list);
        return "carThief";
    }
}
