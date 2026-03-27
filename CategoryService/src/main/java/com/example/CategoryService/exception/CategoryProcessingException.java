package com.example.CategoryService.exception;

public class CategoryProcessingException extends RuntimeException {
    public CategoryProcessingException(String message) {
        super(message);
    }

    public CategoryProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
