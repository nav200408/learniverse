package com.example.CourseService.consumer;

import com.example.CourseService.enums.CourseStatus;
import com.example.CourseService.event.CategoryResponseEvent;
import com.example.CourseService.model.CourseEntity;
import com.example.CourseService.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryResponseConsumer {

    private final CourseRepository courseRepository;

    @Autowired
    public CategoryResponseConsumer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @KafkaListener(topics = "category-response", groupId = "course-group")
    @Transactional
    public void consume(CategoryResponseEvent event) {
        System.out.println("Received Saga response for course: " + event.getCourseId() + " - Success: " + event.isSuccess());
        Optional<CourseEntity> courseOptional = courseRepository.findById(event.getCourseId());
        
        if (courseOptional.isPresent()) {
            CourseEntity course = courseOptional.get();
            if (event.isSuccess()) {
                course.setStatus(CourseStatus.ACTIVE);
            } else {
                course.setStatus(CourseStatus.FAILED);
                // Optionally mark as deleted or keep for retry/manual fix
            }
            courseRepository.save(course);
        } else {
            System.err.println("Course not found: " + event.getCourseId());
        }
    }
}
