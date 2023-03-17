package com.example.carthief.service;

public class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
