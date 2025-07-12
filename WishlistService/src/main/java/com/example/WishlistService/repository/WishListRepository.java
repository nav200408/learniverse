package com.example.WishlistService.repository;

import com.example.WishlistService.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Integer> {
    Optional<WishList> findByUsernameAndCourseId(String username, int courseId);
    @Query("SELECT w.courseId FROM WishList w WHERE w.username = :username")
    List<Integer> findCourseIdsByUsername(String username);
}
