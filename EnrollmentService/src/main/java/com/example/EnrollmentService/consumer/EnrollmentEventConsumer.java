package com.example.EnrollmentService.consumer;
import com.example.EnrollmentService.event.EnrollmentEvent;
import com.example.EnrollmentService.model.EnrollmentEntity;
import com.example.EnrollmentService.repository.EnrollmentRepository;
import com.example.EnrollmentService.service.EnrollmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentEventConsumer {
    private EnrollmentRepository enrollmentRepository;
    private

    @Autowired
    public EnrollmentEventConsumer(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @KafkaListener(id = "EnrollmentGroup", topics = "enrollment")
    public void enrollmentListener(EnrollmentEvent enrollmentEvent) {
        try {
            EnrollmentEntity enrollmentEntity = new EnrollmentEntity(
                    enrollmentEvent.getPaymentId(),
                    enrollmentEvent.getUsername(),
                    enrollmentEvent.getCourseId());
            enrollmentRepository.save(enrollmentEntity);

            ObjectMapper objectMapper = new ObjectMapper();
            OutboxEntity outboxEntity = new OutboxEntity();
            outboxEntity.setTopic(EnrollmentConstants.KAFKA_TOPIC_ENROLLMENT_SUCCESS);
            outboxEntity.setStatus(OutboxStatus.PENDING);
            outboxEntity.setPayload(objectMapper.writeValueAsString(enrollmentEvent));

            outboxRepository.save(outboxEntity);

        } catch (Exception e) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                OutboxEntity outboxEntity = new OutboxEntity();
                outboxEntity.setTopic(EnrollmentConstants.KAFKA_TOPIC_ENROLLMENT_FAIL);
                outboxEntity.setStatus(OutboxStatus.PENDING);
                outboxEntity.setPayload(objectMapper.writeValueAsString(enrollmentEvent));
                outboxRepository.save(outboxEntity);
            } catch (JsonProcessingException jsonException) {
                throw new EnrollmentProcessingException("Failed to serialize enrollment event", jsonException);
            }
        }
    }
}
