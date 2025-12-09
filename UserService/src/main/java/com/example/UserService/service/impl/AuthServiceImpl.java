package com.example.UserService.service.impl;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.dto.response.AuthenticationResponse;
import com.example.UserService.jwt.security.JwtUtils;
import com.example.UserService.jwt.security.UserDetail;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements com.example.UserService.service.AuthService {
    @Autowired
   private UserRepository userRepository;
    @Override
    public ResponseEntity loginHandler(AuthenticationRequest authenticationRequest){
        UserEntity userEntity = userRepository.findByUserName(authenticationRequest.getEmail());

        if (userEntity != null && new BCryptPasswordEncoder().matches(authenticationRequest.getPassword(), userEntity.getPassword())&& userEntity.isAccountNonLock()) {
            UserDetail userDetail = new UserDetail(userEntity);
            String accessToken = JwtUtils.generateAccessToken(userDetail);
            String refreshToken = JwtUtils.generateRefreshToken(userDetail);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken(accessToken);
            authenticationResponse.setRefreshToken(refreshToken);
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>("Your username or password is incorrect", HttpStatus.BAD_REQUEST);
    }
    @Override
    public ResponseEntity<String> registerHandler(RegisterRequest registerRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        if(userRepository.findByUserName(registerRequest.getUsername())!=null){
            return new ResponseEntity<>("username already exist", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerRequest.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        userEntity.setAddress(registerRequest.getAddress());
        userEntity.setAge(registerRequest.getAge());
        userEntity.setFullName(registerRequest.getFullName());
        userEntity.setRole("ROLE_USER");
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setAccountNonLock(true);
        userRepository.saveAndFlush(userEntity);
        return new ResponseEntity<>("register success",HttpStatus.OK);
    }



}
