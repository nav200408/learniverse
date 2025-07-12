package com.example.CourseService.repository;

import com.example.CourseService.model.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<UnitEntity,Integer> {
}
