package com.example.CategoryService.service.impl;

import com.example.CategoryService.client.CourseService;
import com.example.CategoryService.dto.response.CourseDto;
import com.example.CategoryService.repository.CategoryRepository;
import com.example.CategoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseService courseService;
    @Override
    public ResponseEntity<List<CourseDto>> showCourseByCategoryHandler(String categoryName) {
        List<Integer> courseIds = categoryRepository.findCourseIdsByCategoryName(categoryName);
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        List<CourseDto> courseDtos = courseService.getCourseByIds(courseIds,"Bearer "+token);
        return ResponseEntity.ok(courseDtos);
    }
}
