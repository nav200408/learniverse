package com.example.EnrollmentService.consumer;
import com.example.EnrollmentService.constants.EnrollmentConstants;
import com.example.EnrollmentService.event.EnrollmentEvent;
import com.example.EnrollmentService.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentEventConsumer {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentEventConsumer(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @KafkaListener(id = "EnrollmentGroup", topics = EnrollmentConstants.KAFKA_TOPIC_PAYMENT_CREATED_SUCCESS)
    public void enrollmentListener(EnrollmentEvent enrollmentEvent) {
        try {
            enrollmentService.processEnrollment(enrollmentEvent);
        } catch (Exception e) {
            enrollmentService.processEnrollmentFailure(enrollmentEvent);
        }
    }
}
