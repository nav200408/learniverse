package com.example.EnrollmentService.producer;

public interface EnrollmentProducer {
    void publishEnrollmentEvent(String topic, String payload);
}
