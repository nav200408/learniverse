package com.example.UserService.service;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<String> loginHandler(AuthenticationRequest authenticationRequest);
    public ResponseEntity<String> registerHandler(RegisterRequest registerRequest);
}
