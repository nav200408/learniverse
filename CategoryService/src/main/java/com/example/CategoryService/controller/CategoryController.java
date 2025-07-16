package com.example.CategoryService.controller;

import com.example.CategoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/show-course-by-category")
    public ResponseEntity showCourseByCategory(@RequestParam String categoryName){
        return categoryService.showCourseByCategoryHandler(categoryName);
    }
}
