package com.example.UserService.controller;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.service.AuthService;
import com.example.UserService.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest){
        return authService.loginHandler(authenticationRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        return authService.registerHandler(registerRequest);
    }


}
