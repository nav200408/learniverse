package com.example.UserService.controller;

import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user-management")
public class UserManagementController {
    @Autowired
    UserManagementService userManagementService;
    @GetMapping("/show-all-user")
    public List<UserDTOResponse> showAllUser(){
        return userManagementService.showAllUserHandler();
    }
}
