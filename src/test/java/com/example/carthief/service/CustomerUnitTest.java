package com.example.carthief.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerUnitTest {

    @Test
    void shouldCompleteOrder_thenChangeStatus() {
        final Customer customer = CustomerProvider.getCreatedCustomer();

        customer.complete();

        assertEquals(CustomerStatus.COMPLETED,customer.getStatus());
    }


}
