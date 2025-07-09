package com.example.EnrollmentService.service;

import com.example.EnrollmentService.dto.response.EnrollmentResponse;
import com.example.EnrollmentService.model.EnrollmentEntity;
import com.example.EnrollmentService.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @KafkaListener(id = "EnrollmentGroup", topics = "enrollment")
    public void listen(EnrollmentResponse enrollmentResponse) {
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity(enrollmentResponse.getPaymentId(), enrollmentResponse.getUsername(), enrollmentResponse.getCourseId());
        enrollmentRepository.saveAndFlush(enrollmentEntity);
    }
}
