package com.example.carthief.dto;

import com.example.carthief.entity.Car;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarDto {

    private Long id;
    private String name;
    private String brand;
    private Integer year;
    private Integer kilometers;
    private BigDecimal price;

    public CarDto() {
    }

    public CarDto(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.brand = car.getBrand();
        this.year = car.getYear();
        this.kilometers = car.getKilometers();
        this.price = car.getPrice();
    }
}
