package com.example.PaymentService.exception;

public class ResourceNotFoundException extends PaymentException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
