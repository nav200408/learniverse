package com.example.UserService.controller;

import com.example.UserService.dto.request.UserProfileRequest;
import com.example.UserService.dto.response.UserDTOResponse;
import com.example.UserService.dto.response.UserProfileResponse;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.ProfileService;
import com.example.UserService.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    ProfileService profileService;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/showProfile")
    public ResponseEntity<UserProfileResponse> showUserProfile(){
      return profileService.showUserProfileHandler();
    }

    @PostMapping("/editProfile")
    public ResponseEntity<UserProfileResponse> editUserProfile(@RequestBody UserProfileRequest userProfileRequest){
        return profileService.editUserProfileHandler(userProfileRequest);
    }

    @GetMapping("/find-user-by-username")
    public UserDTOResponse userEntity(@RequestParam String username){
        UserEntity userEntity = userRepository.findByUserName(username);
       UserDTOResponse userDTOResponse = new UserDTOResponse(userEntity.getId(),userEntity.getFullName(),userEntity.getAddress(),userEntity.getAge(),userEntity.getUserName(),userEntity.getRole(),userEntity.getEmail());
       return userDTOResponse;
    }

}
