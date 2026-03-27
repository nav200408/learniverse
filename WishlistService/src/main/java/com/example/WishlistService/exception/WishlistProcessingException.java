package com.example.WishlistService.exception;

public class WishlistProcessingException extends RuntimeException {
    public WishlistProcessingException(String message) {
        super(message);
    }

    public WishlistProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
