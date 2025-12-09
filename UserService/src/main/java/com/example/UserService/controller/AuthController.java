package com.example.UserService.controller;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.jwt.security.JwtUtils;
import com.example.UserService.jwt.security.UserDetail;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.AuthService;
import com.example.UserService.service.impl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest){
        return authService.loginHandler(authenticationRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        return authService.registerHandler(registerRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestParam String refreshToken) {

            if (JwtUtils.validateToken(refreshToken) && JwtUtils.isRefreshToken(refreshToken)) {
                String username = JwtUtils.getUsernameFromToken(refreshToken);
                UserEntity user = userRepository.findByUserName(username);
                if (user == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
                }

                String newAccessToken = JwtUtils.generateAccessToken(new UserDetail(user));
                return ResponseEntity.ok(newAccessToken);
            }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
    }


}
