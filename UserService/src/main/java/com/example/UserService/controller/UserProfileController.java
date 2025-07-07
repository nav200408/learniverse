package com.example.UserService.controller;

import com.example.UserService.dto.request.UserProfileRequest;
import com.example.UserService.dto.response.UserProfileResponse;
import com.example.UserService.service.ProfileService;
import com.example.UserService.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    ProfileService profileService;
    @GetMapping("/showProfile")
    public ResponseEntity<UserProfileResponse> showUserProfile(){
      return profileService.showUserProfileHandler();
    }

    @PostMapping("/editProfile")
    public ResponseEntity<UserProfileResponse> editUserProfile(@RequestBody UserProfileRequest userProfileRequest){
        return profileService.editUserProfileHandler(userProfileRequest);
    }

}
