package com.example.CategoryService.producer;

public interface CategoryProducer {
    void sendMessage(String topic, String payload);
}
