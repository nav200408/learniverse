package com.example.EnrollmentService.producer.Impl;

import com.example.EnrollmentService.producer.EnrollmentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentProducerImpl implements EnrollmentProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public EnrollmentProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishEnrollmentEvent(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
