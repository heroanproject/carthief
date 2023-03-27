package com.example.carthief.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    String name;
    BigDecimal price;
    String brand;
    Integer kilometers;
    Integer year;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(name, car.name) && Objects.equals(price, car.price) && Objects.equals(brand, car.brand) && Objects.equals(kilometers, car.kilometers) && Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, brand, kilometers, year);
    }
}
