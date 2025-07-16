package com.example.CategoryService.service;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity showCourseByCategoryHandler(String categoryName);
}
