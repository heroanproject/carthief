package com.example.carthief.mapper;

import com.example.carthief.dto.DealerDto;
import com.example.carthief.entity.Dealer;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Mapper() {
    }

    public Dealer mapToDealer(DealerDto dealerDto){
        return new Dealer(dealerDto.getId(), dealerDto.getName(), dealerDto.getCars());
    }
}
