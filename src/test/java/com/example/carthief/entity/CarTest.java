package com.example.carthief.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void testHashCode() {
        Car car1 = new Car();
        Car car2 = new Car();
        assertEquals(car1.hashCode(), car2.hashCode());
    }

}