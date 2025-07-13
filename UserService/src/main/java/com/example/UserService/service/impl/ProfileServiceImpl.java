package com.example.UserService.service.impl;

import com.example.UserService.dto.request.UserProfileRequest;
import com.example.UserService.dto.response.UserProfileResponse;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<UserProfileResponse> showUserProfileHandler(){
        System.out.println("hello");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
       UserEntity userEntity = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
       UserProfileResponse userProfileResponse = new UserProfileResponse(userEntity.getUserName(),userEntity.getAge(),userEntity.getFullName(),userEntity.getAddress(), userEntity.getEmail());
       return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserProfileResponse> editUserProfileHandler(UserProfileRequest userProfileRequest) {
       UserEntity userEntity = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
       userEntity.setFullName(userProfileRequest.getFullName());
       userEntity.setAge(userProfileRequest.getAge());
       userEntity.setAddress(userProfileRequest.getAddress());
       userRepository.save(userEntity);
        return ResponseEntity.ok().body(new UserProfileResponse(userEntity.getUserName(),userEntity.getAge(),userEntity.getFullName(),userEntity.getAddress(), userEntity.getEmail()));
    }
}
