package com.example.carthief.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testHashCode () {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("John");

        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Jane");

        Car car1 = new Car();
        car1.setId(1L);
        car1.setName("BMW");

        Car car2 = new Car();
        car2.setId(2L);
        car2.setName("Toyota");

        person1.setCar(car1);
        person2.setCar(car2);

        int hashCode1 = person1.hashCode();
        int hashCode2 = person2.hashCode();


    }
}