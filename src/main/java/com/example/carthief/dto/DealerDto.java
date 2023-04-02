package com.example.carthief.dto;

import com.example.carthief.entity.Car;
import com.example.carthief.entity.Dealer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DealerDto {

    private Long id;
    private String name;
    private Set<Car> cars;

    public DealerDto(Dealer dealer) {
        this.id = dealer.getId();
        this.name = dealer.getName();
        this.cars = dealer.getCars();
    }
}
