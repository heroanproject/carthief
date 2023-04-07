package com.example.carthief.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer;
    private Car car1, car2;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
        dealer.setId(1L);
        dealer.setName("Test Dealer");

        car1 = new Car();
        car1.setId(1L);
        car1.setName("Car 1");
        car1.setBrand("Brand 1");
        car1.setYear(2022);
        car1.setKilometers(0);
        car1.setPrice(BigDecimal.valueOf(20000));

        car2 = new Car();
        car2.setId(2L);
        car2.setName("Car 2");
        car2.setBrand("Brand 2");
        car2.setYear(2021);
        car2.setKilometers(100);
        car2.setPrice(BigDecimal.valueOf(15000));

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        dealer.setCars(cars);
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = 10;
        int actualHashCode = dealer.hashCode();
        Assertions.assertEquals(expectedHashCode, actualHashCode);
    }

}