package com.example.carthief.dto;

import com.example.carthief.entity.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DealerDto {

    private Long id;
    private String name;
    private Set<Car> cars;

    public DealerDto(Long id, String name, Set<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }
}
