package com.example.UserService.service.impl;

import com.example.UserService.dto.request.AuthenticationRequest;
import com.example.UserService.dto.request.RegisterRequest;
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
    public ResponseEntity<String> loginHandler(AuthenticationRequest authenticationRequest){
        UserEntity userEntity = userRepository.findByUserName(authenticationRequest.getEmail());
        if(userEntity !=null||userEntity.getPassword().equals(authenticationRequest.getPassword())){
            return new ResponseEntity<>(JwtUtils.generateToken(new UserDetail(userEntity)), HttpStatus.OK);
        }
        return new ResponseEntity<>("Your username or password not correct", HttpStatus.BAD_REQUEST);
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
        userRepository.saveAndFlush(userEntity);
        return new ResponseEntity<>("register success",HttpStatus.OK);
    }



}
