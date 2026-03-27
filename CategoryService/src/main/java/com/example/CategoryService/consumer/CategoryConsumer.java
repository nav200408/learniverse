package com.example.CategoryService.consumer;

import com.example.CategoryService.enums.OutboxStatus;
import com.example.CategoryService.event.CategoryEvent;
import com.example.CategoryService.event.CategoryResponseEvent;
import com.example.CategoryService.model.CategoryEntity;
import com.example.CategoryService.model.OutboxEntity;
import com.example.CategoryService.repository.CategoryRepository;
import com.example.CategoryService.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryConsumer {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(id = "CategoryGroup", topics = "category")
    public void listen(CategoryEvent categoryEvent) {
        boolean success = false;
        String message = "";
        try {
            if (categoryEvent.getType().equals("create")) {
                System.out.println("hello");
                CategoryEntity category = new CategoryEntity(categoryEvent.getCategory(), categoryEvent.getCourseId());
                categoryRepository.saveAndFlush(category);
                success = true;
                message = "Category created successfully";
            } else {
                System.out.println("hi");
                CategoryEntity category = categoryRepository.findByCourseId(categoryEvent.getCourseId()).get(0);
                category.setCategoryName(categoryEvent.getCategory());
                categoryRepository.saveAndFlush(category);
                success = true;
                message = "Category updated successfully";
            }
        } catch (Exception e) {
            success = false;
            message = "Failed to process category: " + e.getMessage();
        }

        // Send response via Outbox
        CategoryResponseEvent response = new CategoryResponseEvent(categoryEvent.getCourseId(), success, message);
        try {
            String payload = objectMapper.writeValueAsString(response);
            OutboxEntity outbox = new OutboxEntity("category-response", payload, OutboxStatus.PENDING);
            outboxRepository.save(outbox);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to serialize category response: " + e.getMessage());
        }
    }
}
