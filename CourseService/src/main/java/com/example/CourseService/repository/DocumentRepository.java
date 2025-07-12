package com.example.CourseService.repository;

import com.example.CourseService.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity,Integer> {
}
