package com.example.PaymentService.producer;

public interface PaymentProducer {
    public void publishPaymentCompletedEvent(String topic, String payload);
    public void publishPaymentProcessingCompleteEvent(String topic, String payload);
}
