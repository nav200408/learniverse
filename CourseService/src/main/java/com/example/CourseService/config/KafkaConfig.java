package com.example.CourseService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
public class KafkaConfig {
    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public NewTopic categoryTopic() {
        return TopicBuilder.name("category")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic categoryResponseTopic() {
        return TopicBuilder.name("category-response")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
