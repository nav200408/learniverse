package com.example.EnrollmentService.service;

import com.example.EnrollmentService.dto.response.CourseDto;
import com.example.EnrollmentService.event.EnrollmentEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnrollmentService {
    void processEnrollment(EnrollmentEvent enrollmentEvent);
    List<CourseDto> getAllMyCourse();
    ResponseEntity<?> checkEnrollment(int courseId);
}
