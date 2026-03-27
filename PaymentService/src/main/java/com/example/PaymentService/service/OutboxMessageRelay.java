package com.example.PaymentService.service;

public interface OutboxMessageRelay {
    public void publishMessages();
}
