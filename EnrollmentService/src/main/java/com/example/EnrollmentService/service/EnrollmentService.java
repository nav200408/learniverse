package com.example.EnrollmentService.service;

import com.example.EnrollmentService.dto.response.CourseDto;
import com.example.EnrollmentService.event.EnrollmentEvent;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EnrollmentService {
    List<CourseDto> getAllMyCourse();
    ResponseEntity<?> checkEnrollment(int courseId);
    void processEnrollment(EnrollmentEvent enrollmentEvent) throws JsonProcessingException;
    void processEnrollmentFailure(EnrollmentEvent enrollmentEvent);
}
