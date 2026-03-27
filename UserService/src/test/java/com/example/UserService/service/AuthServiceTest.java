package com.example.UserService.service;

import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.exception.UserProcessingException;
import com.example.UserService.model.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceTest {
    @MockitoBean
    private UserRepository userRepository;
    @Test
    void createUserSuccess(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setPassword("123bgnhmj");
        registerRequest.setFullName("Nguyen Anh Vu");

        UserEntity mockSavedUser = new UserEntity();
        mockSavedUser.setAddress("481 D4 Tân Mai");
        mockSavedUser.setAge(18);
        mockSavedUser.setRole("USER");
        mockSavedUser.setAccountNonLock(true);
        mockSavedUser.setEmail("anhvu08032004@gmail.com");
        mockSavedUser.setUserName("nav200408");
        mockSavedUser.setFullName("Nguyen Anh Vu");

        when(userRepository.findByUserName(registerRequest.getUsername())).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockSavedUser);
        AuthService authService = new AuthServiceImpl(userRepository);
        ResponseEntity<?> response = authService.registerHandler(registerRequest);
        Assertions.assertNotNull(response, "The response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be 200 OK");
        Assertions.assertEquals("register success", response.getBody());
    }

    @Test
    void createUserFail_DuplicatedUserName(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setPassword("123bgnhmj");
        registerRequest.setFullName("Nguyen Anh Vu");

        UserEntity mockSavedUser = new UserEntity();
        mockSavedUser.setAddress("481 D4 Tân Mai");
        mockSavedUser.setAge(18);
        mockSavedUser.setRole("USER");
        mockSavedUser.setAccountNonLock(true);
        mockSavedUser.setEmail("anhvu08032004@gmail.com");
        mockSavedUser.setUserName("nav200408");
        mockSavedUser.setFullName("Nguyen Anh Vu");

        when(userRepository.findByUserName(registerRequest.getUsername())).thenReturn(mockSavedUser);
        when(userRepository.save(any(UserEntity.class))).thenReturn(mockSavedUser);
        AuthService authService = new AuthServiceImpl(userRepository);
        Assertions.assertThrowsExactly(UserProcessingException.class,()->authService.registerHandler(registerRequest),"username already exist");
    }
}
