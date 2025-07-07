package com.example.receiveData.client;

import com.example.receiveData.dto.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "UserService", fallback = ProfileServiceFallBack.class)
public interface ProfileService {
    @GetMapping("/profile/showProfile")
    public ResponseEntity<UserProfileResponse> showUserProfile(@RequestHeader("Authorization") String authHeader);
}
@Component
class ProfileServiceFallBack implements ProfileService{

    @Override
    public ResponseEntity<UserProfileResponse> showUserProfile(@RequestHeader("Authorization") String authHeader) {
        UserProfileResponse fallback = new UserProfileResponse();
        fallback.setFullName("Unknown User");
        return ResponseEntity.ok(fallback);
    }
}
