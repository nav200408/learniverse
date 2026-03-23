package com.example.UserService.exception;

public class UserProcessingException extends RuntimeException {
    public UserProcessingException(String message) {
        super(message);
    }

    public UserProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
