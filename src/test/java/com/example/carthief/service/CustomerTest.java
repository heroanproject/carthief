package com.example.carthief.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private UUID id = UUID.randomUUID();;
    private String name = "Henrik";;
    private String phoneNumber = "0731231232";


    @Test
    void customerTestConstructorsSets () {

        CustomerStatus customerStatus = CustomerStatus.CREATED;

        Customer customer = new Customer(id,name,phoneNumber);

        assertNotNull(customer);
        assertEquals(id, customer.getId());
        assertEquals(customerStatus, CustomerStatus.CREATED);
        assertEquals(name, customer.getName());
        assertEquals(phoneNumber, customer.getPhoneNumber());
    }

    @Test
    void CustomerSetterMethodShouldUpdateInstanceVariables () {
        Customer customer = new Customer(id, name, phoneNumber);

        String newName = "Anton";
        String newPhoneNumber = "123456";

        customer.setName(newName);
        customer.setPhoneNumber(newPhoneNumber);
        assertEquals(newName, customer.getName());
        assertEquals(newPhoneNumber, customer.getPhoneNumber());

    }

    @Test
    void CustomerEqualsAndHashCOdeShouldReturnTrueForSameAttributes () {

        Customer customer1 = new Customer(id, name, phoneNumber);
        Customer customer2 = new Customer(id, name, phoneNumber);

        assertEquals(customer1, customer2);
        assertEquals(customer2, customer1);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }
}