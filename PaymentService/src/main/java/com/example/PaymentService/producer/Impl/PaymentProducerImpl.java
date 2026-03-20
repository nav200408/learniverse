package com.example.PaymentService.producer.Impl;

import com.example.PaymentService.producer.PaymentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducerImpl implements PaymentProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishPaymentCompletedEvent(String topic, String payload) {
        kafkaTemplate.send(topic,payload);
    }

    public void publishPaymentProcessingCompleteEvent(String topic, String payload){
        kafkaTemplate.send(topic,payload);
    }
}
