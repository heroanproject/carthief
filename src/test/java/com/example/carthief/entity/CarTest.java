package com.example.carthief.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void CarTestConstructorSetsInstanceAndRetrieveGetterMethods() {
        UUID id = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(350000);
        String name = "Volvo";

        Car car = new Car(id, price, name);

        assertNotNull(car);
        assertEquals(id, car.getId());
        assertEquals(price, car.getPrice());
        assertEquals(name, car.getName());
    }
    @Test
    void CarSetterMethodsShouldUpdateInstanceVariables() {
        UUID id = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(500000);
        String name = "Tesla Model X";
        Car car = new Car(id, price, name);

        BigDecimal newPrice = BigDecimal.valueOf(550000);
        String newName = "Tesla Model Y";

        car.setPrice(newPrice);
        car.setName(newName);
        assertEquals(newPrice, car.getPrice());
        assertEquals(newName, car.getName());
    }

    @Test
    void CarEqualsAndHashCodeShouldReturnTrueForSameAttributes() {
        UUID id = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(300);
        String name = "Mulle Mecks bil";

        Car car1 = new Car(id, price, name);
        Car car2 = new Car(id, price, name);

        assertEquals(car1, car2);
        assertEquals(car2, car1);
        assertEquals(car1.hashCode(), car2.hashCode());
    }

}