package com.example.carthief.controller;

import com.example.carthief.dto.DealerDto;
import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import com.example.carthief.mapper.Mapper;
import com.example.carthief.repository.CarRepository;
import com.example.carthief.repository.DealerRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebController {

    private final DealerRepository dealerRepository;
    private final CarRepository carRepository;
    private final Mapper mapper;

    public WebController(DealerRepository dealerRepository, CarRepository carRepository, Mapper mapper) {
        this.dealerRepository = dealerRepository;
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @GetMapping("/carthief")
    String dealers(Model model){
        model.addAttribute("listDealers", dealerRepository.findAll());
        return "carThief";
    }

    @GetMapping("/search")
    String searchByCarName(Model model, @Param("keyword") String keyword){
        List<Dealer> list = dealerRepository.findByCarsNameOrCarsBrand(keyword, keyword);
        model.addAttribute("listDealers", list);
        return "carThief";
    }

    @PostMapping("/save")
    String addCarToDealer(@ModelAttribute("dealerId") Long dealerId, @ModelAttribute Car car){
        var savedCar = carRepository.save(car);
        var dealer = dealerRepository.findById(dealerId).orElseThrow();
        dealer.getCars().add(savedCar);
        dealerRepository.save(dealer);
        return "redirect:carthief";
    }

    @PostMapping("/savedealer")
    String addDealer(@ModelAttribute DealerDto dealerDto){
        dealerRepository.save(mapper.mapToDealer(dealerDto));
        return "redirect:carthief";
    }
}
