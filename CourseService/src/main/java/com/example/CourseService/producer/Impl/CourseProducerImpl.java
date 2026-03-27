package com.example.CourseService.producer.Impl;

import com.example.CourseService.producer.CourseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseProducerImpl implements CourseProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public CourseProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
    }
}
