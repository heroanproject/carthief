package com.example.carthief.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private CustomerStatus status;
    private String name;
    private String phoneNumber;




    @JsonCreator
    public Customer(@JsonProperty("id") final UUID id, @JsonProperty("name")
                    String name, @JsonProperty ("phoneNumber") String phoneNumber) {
        this.id = id != null ? id : UUID.randomUUID();
        this.status = CustomerStatus.CREATED;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public CustomerStatus getStatus () {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void complete(){
        validateState();
        this.status = CustomerStatus.COMPLETED;
    }

    private void validateState () {
        if (CustomerStatus.COMPLETED.equals(status)) {
            throw new DomainException("The customer is in completed state.");
        }
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id,name,phoneNumber);
    }
}
