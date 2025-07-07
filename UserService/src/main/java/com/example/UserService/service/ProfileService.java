package com.example.UserService.service;

import com.example.UserService.dto.request.UserProfileRequest;
import com.example.UserService.dto.response.UserProfileResponse;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<UserProfileResponse> showUserProfileHandler();
    public ResponseEntity<UserProfileResponse> editUserProfileHandler(UserProfileRequest userProfileRequest);
}
