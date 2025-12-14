package com.example.CourseService.repository;

import com.example.CourseService.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryKafkaEvent extends JpaRepository<CategoryDto,Integer> {
}
