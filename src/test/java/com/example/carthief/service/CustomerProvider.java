package com.example.carthief.service;

import java.util.UUID;

public class CustomerProvider {

    public static Customer getCreatedCustomer () {
        return new Customer(UUID.randomUUID(), "test", "test");
    }

    public static Customer getCompletedCustomer () {
        final Customer customer = getCreatedCustomer();
        customer.complete();
        return customer;
    }
}
