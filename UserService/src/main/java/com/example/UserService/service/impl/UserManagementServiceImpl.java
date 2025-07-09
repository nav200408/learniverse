package com.example.UserService.service.impl;

import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
                        user.getRole(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> lockUserHandler(String username) {
       UserEntity userEntity = userRepository.findByUserName(username);
       userEntity.setAccountNonLock(false);
        return ResponseEntity.ok().body("account is locked");
    }

    public Page<UserEntity> searchUsers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByUserNameContainingIgnoreCase(keyword, pageable);
    }
}
