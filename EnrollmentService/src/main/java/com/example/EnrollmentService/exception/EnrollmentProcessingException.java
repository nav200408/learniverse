package com.example.EnrollmentService.exception;

public class EnrollmentProcessingException extends RuntimeException {
    public EnrollmentProcessingException(String message) {
        super(message);
    }

    public EnrollmentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
