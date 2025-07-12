package com.example.CourseService.repository;

import com.example.CourseService.model.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    Page<CourseEntity> findAll(Pageable pageable);
    Page<CourseEntity> findAllByIsDeleteFalseAndIsPublishTrue(Pageable pageable);
    @Query("SELECT c FROM CourseEntity c " +
            "WHERE (LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.courseDetail) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND c.isDelete = false AND c.isPublish = true")
    List<CourseEntity> searchCourses(@Param("keyword") String keyword);
    @Query(
            value = "SELECT * FROM course_entity WHERE is_delete = false",
            countQuery = "SELECT COUNT(*) FROM course_entity WHERE is_delete = false",
            nativeQuery = true
    )
    Page<CourseEntity> findAllByIsDeleteFalse(Pageable pageable);
}
