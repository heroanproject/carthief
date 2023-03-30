package com.example.carthief.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    String name;
    String brand;
    Integer year;
    Integer kilometers;
    BigDecimal price;

    public Car(String name, String brand, Integer year, Integer kilometers, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.kilometers = kilometers;
        this.price = price;
    }

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
