package com.example.EnrollmentService.controller;

import com.example.EnrollmentService.dto.response.CourseDto;
import com.example.EnrollmentService.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/my-course")
    public List<CourseDto> getAllMyCourse() {
        return enrollmentService.getAllMyCourse();
    }

    @GetMapping("/is-enrolled")
    public ResponseEntity<?> checkEnrollment(@RequestParam int courseId) {
        return enrollmentService.checkEnrollment(courseId);
    }
}
