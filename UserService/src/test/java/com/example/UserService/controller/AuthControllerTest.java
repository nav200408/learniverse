package com.example.UserService.controller;

import com.example.UserService.dto.request.RegisterRequest;
import com.example.UserService.exception.UserProcessingException;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AuthService authService;
    @MockitoBean
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    @WithMockUser
    void createUserSuccess() throws Exception{
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setPassword("1234BGnhMJ@");
        registerRequest.setFullName("Nguyen Anh Vu");

       when(authService.registerHandler(any(RegisterRequest.class))).thenReturn(new ResponseEntity<>("register success", HttpStatus.OK));

        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("register success"));
    }
    @Test
    @WithMockUser
    void createUserWithSameEmail() throws Exception{
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setPassword("1234BGnhMJ@");
        registerRequest.setFullName("Nguyen Anh Vu");

        when(authService.registerHandler(any(RegisterRequest.class))).thenThrow(new UserProcessingException("Username already existed"));

        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Username already existed"))
                .andExpect(jsonPath("$.path").value("/api/auth/register"));
    }

    @Test
    @WithMockUser
    void createUserLackingRequiredField() throws Exception{
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setFullName("Nguyen Anh Vu");

        when(authService.registerHandler(any(RegisterRequest.class))).thenReturn(new ResponseEntity<>("register success", HttpStatus.OK));

        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser
    void createUser_WrongDataFormat() throws Exception{
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setAddress("481 D4 Tân Mai");
        registerRequest.setAge(18);
        registerRequest.setUsername("nav200408");
        registerRequest.setEmail("anhvu08032004@gmail.com");
        registerRequest.setFullName("Nguyen Anh Vu");
        registerRequest.setPassword("123bgnhmj");
        when(authService.registerHandler(any(RegisterRequest.class))).thenReturn(new ResponseEntity<>("register success", HttpStatus.OK));

        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest());

    }
}
