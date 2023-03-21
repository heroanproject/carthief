package com.example.carthief.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void CarTestConstructorSetsInstanceAndReturnsGetterMethods() {
        Long id = 1L;
        BigDecimal price = BigDecimal.valueOf(350000);
        String name = "Volvo";

        Car car = new Car(id, name, price);

        assertNotNull(car);
        assertEquals(id, car.getId());
        assertEquals(price, car.getPrice());
        assertEquals(name, car.getName());
    }
    @Test
    void CarSetterMethodsShouldUpdateInstanceVariables() {
        Long id = 2L;
        BigDecimal price = BigDecimal.valueOf(500000);
        String name = "Tesla Model X";
        Car car = new Car(id, name, price);

        BigDecimal newPrice = BigDecimal.valueOf(550000);
        String newName = "Tesla Model Y";

        car.setPrice(newPrice);
        car.setName(newName);
        assertEquals(newPrice, car.getPrice());
        assertEquals(newName, car.getName());
    }


}