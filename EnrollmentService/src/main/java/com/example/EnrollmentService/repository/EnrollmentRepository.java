package com.example.EnrollmentService.repository;

import com.example.EnrollmentService.model.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Integer> {
    @Query("SELECT e.courseId FROM EnrollmentEntity e WHERE e.username = :username")
    List<Integer> findCourseIdsByUsername(@Param("username") String username);

    Optional<EnrollmentEntity> findByUsernameAndCourseId(String username, int courseId);
}
