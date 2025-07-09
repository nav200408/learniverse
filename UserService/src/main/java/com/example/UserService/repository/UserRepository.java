package com.example.UserService.repository;

import com.example.UserService.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByUserName(String userName);

    Page<UserEntity> findByUserNameContainingIgnoreCase(String keyword, Pageable pageable);
}
