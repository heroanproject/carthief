package com.example.carthief.mapper;

import com.example.carthief.dto.DealerDto;
import com.example.carthief.entity.Dealer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public Mapper() {
    }

    public Dealer mapDtoToDealer(DealerDto dealerDto){
        return new Dealer(dealerDto.getId(), dealerDto.getName(), dealerDto.getCars());
    }

    public DealerDto mapDealerToDto(Dealer dealer){
        return new DealerDto(dealer);
    }

    public List<DealerDto> mapDealerToDto(List<Dealer> dealers){
        return dealers.stream().map(DealerDto::new).toList();
    }
}
