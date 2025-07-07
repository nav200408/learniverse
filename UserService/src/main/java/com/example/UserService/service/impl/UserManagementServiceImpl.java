package com.example.UserService.service.impl;

import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTOResponse> showAllUserHandler() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(user -> new UserDTOResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getAddress(),
                        user.getAge(),
                        user.getUserName(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }
}
