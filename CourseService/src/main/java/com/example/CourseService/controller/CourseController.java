package com.example.CourseService.controller;

import com.example.CourseService.dto.CourseDto;
import com.example.CourseService.model.CourseEntity;
import com.example.CourseService.repository.CourseRepository;
import com.example.CourseService.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;
    @GetMapping("/show-all")
    public Page<CourseDto> showAllCourse(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
       return courseService.showAllCourseHandler(page,size);
    }

    @GetMapping("/show-detail")
    public CourseEntity showCourseDetail(@RequestParam int courseId){
        return courseService.showCourseDetailHandler(courseId);
    }

    @PostMapping("/get-course-by-ids")
    public List<CourseDto> getCourseByIds(@RequestBody List<Integer> courseIds){
        List<CourseEntity> courseEntities = courseRepository.findAllById(courseIds);
        return courseEntities.stream().map(course-> new CourseDto(course.getCourseName(),course.getCourseDetail(),course.getPrice(),course.getCourseImage(),course.getCourseId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/search-course")
    public List<CourseDto> searchCourse(@RequestParam String keyword){
        List<CourseEntity> courseEntities = courseRepository.searchCourses(keyword);
       return courseEntities.stream().map(course-> new CourseDto(course.getCourseName(),course.getCourseDetail(),course.getPrice(),course.getCourseImage(),course.getCourseId()))
               .collect(Collectors.toList());
    }
}
