package com.example.CategoryService.repository;

import com.example.CategoryService.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    @Query("SELECT c.courseId FROM CategoryEntity c WHERE c.categoryName = :categoryName")
    List<Integer> findCourseIdsByCategoryName(@Param("categoryName") String categoryName);

    List<CategoryEntity> findByCourseId(int courseId);
}
