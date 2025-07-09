package com.example.PaymentService.client;

import com.example.PaymentService.dto.response.UserDTOResponse;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserService", fallback = UserServiceFallBack.class )
public interface UserService {
    @GetMapping("/profile/find-user-by-username")
    public UserDTOResponse userEntity(@RequestParam String username, @RequestHeader("Authorization") String authHeader);
}
@Component
class UserServiceFallBack implements UserService{

    @Override
    public UserDTOResponse userEntity(String username, String authHeader) {
        return null;
    }
}