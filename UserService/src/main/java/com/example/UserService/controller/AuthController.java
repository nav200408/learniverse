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

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Login request: {}", authenticationRequest);
        return authService.loginHandler(authenticationRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest registerRequest) {
        logger.info("Register request: {}", registerRequest);
        return authService.registerHandler(registerRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestParam String refreshToken) {
        logger.info("Refresh token request: {}", refreshToken);
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
