package com.example.UserService.service;

import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserManagementService {
    public List<UserDTOResponse> showAllUserHandler();

    ResponseEntity<String> lockUserHandler(String username);

    public Page<UserEntity> searchUsers(String keyword, int page, int size);
}
