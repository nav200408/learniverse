package com.example.EnrollmentService.service;

public interface OutboxMessageRelay {
    void publishMessages();
    void cleanupProcessedMessages();
}
