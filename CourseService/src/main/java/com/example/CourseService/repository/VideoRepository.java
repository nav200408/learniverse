package com.example.CourseService.repository;

import com.example.CourseService.model.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity,Integer> {
}
