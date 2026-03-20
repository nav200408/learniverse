package com.example.CategoryService.producer.impl;

import com.example.CategoryService.producer.CategoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryProducerImpl implements CategoryProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public CategoryProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
