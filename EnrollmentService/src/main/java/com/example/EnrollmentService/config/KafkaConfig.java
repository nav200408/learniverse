package com.example.EnrollmentService.config;

import com.example.EnrollmentService.constants.EnrollmentConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
public class KafkaConfig {
    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public NewTopic enrollmentProcessSuccessfully() {
        return new NewTopic(EnrollmentConstants.KAFKA_TOPIC_ENROLLMENT_SUCCESS, 1, (short) 1);
    }

    @Bean
    public NewTopic enrollmentProcessFail() {
        return new NewTopic(EnrollmentConstants.KAFKA_TOPIC_ENROLLMENT_FAIL, 1, (short) 1);
    }
}
