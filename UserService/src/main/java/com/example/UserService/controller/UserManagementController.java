package com.example.UserService.controller;

import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.model.UserEntity;
import com.example.UserService.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/lock-user")
    public ResponseEntity<String> lockUser(@RequestParam String username){
        return userManagementService.lockUserHandler(username);
    }
    @GetMapping("/search")
    public Page<UserEntity> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userManagementService.searchUsers(keyword, page, size);
    }
}
