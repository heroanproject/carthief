package com.example.carthief.controller;

import com.example.carthief.entity.Dealer;
import com.example.carthief.repository.DealerRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    private final DealerRepository dealerRepository;

    public WebController(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
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
}
