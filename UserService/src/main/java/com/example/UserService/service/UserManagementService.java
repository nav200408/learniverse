package com.example.UserService.service;

import com.example.UserService.dto.response.UserDTOResponse;

import java.util.List;

public interface UserManagementService {
    public List<UserDTOResponse> showAllUserHandler();
}
