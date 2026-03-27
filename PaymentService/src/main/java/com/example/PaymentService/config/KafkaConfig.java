package com.example.PaymentService.config;

import com.example.PaymentService.constants.PaymentConstants;
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
    public NewTopic paymentCreatedSuccess() {
        return new NewTopic(
                PaymentConstants.KAFKA_TOPIC_PAYMENT_CREATED_SUCCESS,
                1,
                (short) 1);
    }

    @Bean
    public NewTopic paymentProcessingSuccess() {
        return new NewTopic(
                PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_SUCCESS,
                1,
                (short) 1);
    }

    @Bean
    public NewTopic paymentProcessingFail() {
        return new NewTopic(
                PaymentConstants.KAFKA_TOPIC_PAYMENT_PROCESSING_FAIL,
                1,
                (short) 1);
    }
}
