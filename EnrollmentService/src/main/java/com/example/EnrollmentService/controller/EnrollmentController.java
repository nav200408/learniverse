package com.example.EnrollmentService.controller;

import com.example.EnrollmentService.client.CourseService;
import com.example.EnrollmentService.dto.response.CourseDto;
import com.example.EnrollmentService.model.EnrollmentEntity;
import com.example.EnrollmentService.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    CourseService courseService;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @GetMapping("/my-course")
    public List<CourseDto> getAllMyCourse(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Integer> courseIds = enrollmentRepository.findCourseIdsByUsername(username);
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return courseService.getCourseByIds(courseIds,"Bearer "+token);
    }

    @GetMapping("/is-enrolled")
    public ResponseEntity checkEnrollment(@RequestParam int courseId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username"+username);
        Optional<EnrollmentEntity> enrollmentEntity = enrollmentRepository.findByUsernameAndCourseId(username,courseId);
        if(enrollmentEntity.isPresent()){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
