package com.example.CourseService.exception;

public class CourseProcessingException extends RuntimeException {
    public CourseProcessingException(String message) {
        super(message);
    }

    public CourseProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
