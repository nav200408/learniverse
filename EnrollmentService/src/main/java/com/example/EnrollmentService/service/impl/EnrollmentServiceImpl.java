package com.example.EnrollmentService.service.impl;

import com.example.EnrollmentService.client.CourseService;
import com.example.EnrollmentService.constants.EnrollmentConstants;
import com.example.EnrollmentService.dto.response.CourseDto;
import com.example.EnrollmentService.enums.OutboxStatus;
import com.example.EnrollmentService.event.EnrollmentEvent;
import com.example.EnrollmentService.exception.EnrollmentProcessingException;
import com.example.EnrollmentService.model.EnrollmentEntity;
import com.example.EnrollmentService.model.OutboxEntity;
import com.example.EnrollmentService.repository.EnrollmentRepository;
import com.example.EnrollmentService.repository.OutboxRepository;
import com.example.EnrollmentService.service.EnrollmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final OutboxRepository outboxRepository;
    private final CourseService courseService;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, OutboxRepository outboxRepository, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.outboxRepository = outboxRepository;
        this.courseService = courseService;
    }

    @Override
    public List<CourseDto> getAllMyCourse() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Integer> courseIds = enrollmentRepository.findCourseIdsByUsername(username);
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return courseService.getCourseByIds(courseIds, "Bearer " + token);
    }

    @Override
    public ResponseEntity<?> checkEnrollment(int courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username" + username);
        Optional<EnrollmentEntity> enrollmentEntity = enrollmentRepository.findByUsernameAndCourseId(username, courseId);
        if (enrollmentEntity.isPresent()) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
