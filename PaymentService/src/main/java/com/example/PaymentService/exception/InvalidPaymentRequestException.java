package com.example.PaymentService.exception;

public class InvalidPaymentRequestException extends PaymentException {
    public InvalidPaymentRequestException(String message) {
        super(message);
    }
}
