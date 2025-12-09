package com.example.UserService.service;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity loginHandler(AuthenticationRequest authenticationRequest);
    public ResponseEntity<String> registerHandler(RegisterRequest registerRequest);
}
