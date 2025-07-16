package com.example.CategoryService.service.impl;

import com.example.CategoryService.client.CourseService;
import com.example.CategoryService.dto.request.CategoryDto;
import com.example.CategoryService.dto.response.CourseDto;
import com.example.CategoryService.model.CategoryEntity;
import com.example.CategoryService.repository.CategoryRepository;
import com.example.CategoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
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
    public ResponseEntity showCourseByCategoryHandler(String categoryName) {
        List<Integer> courseIds = categoryRepository.findCourseIdsByCategoryName(categoryName);
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        List<CourseDto> courseDtos = courseService.getCourseByIds(courseIds,"Bearer "+token);
        return ResponseEntity.ok(courseDtos);
    }
    @KafkaListener(id = "CategoryGroup", topics = "category")
    public void listen(CategoryDto categoryDto) {
        if(categoryDto.getType().equals("create")){
            System.out.println("hello");
            CategoryEntity category = new CategoryEntity(categoryDto.getCategory(), categoryDto.getCourseId());
            categoryRepository.saveAndFlush(category);
        }
        else {
            System.out.println("hi");
            CategoryEntity category = categoryRepository.findByCourseId(categoryDto.getCourseId()).get(0);
            category.setCategoryName(categoryDto.getCategory());
            categoryRepository.saveAndFlush(category);
        }
    }
}
