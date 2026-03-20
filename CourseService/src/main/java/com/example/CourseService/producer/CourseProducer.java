package com.example.CourseService.producer;

public interface CourseProducer {
    void sendMessage(String topic, String payload);
}
