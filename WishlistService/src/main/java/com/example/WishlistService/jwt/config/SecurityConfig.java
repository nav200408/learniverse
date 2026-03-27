package com.example.WishlistService.jwt.config;




import com.example.WishlistService.jwt.security.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/payment/vnpay-payment-return/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .anyRequest().hasAuthority("ROLE_USER")
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        // Triggers when there is NO token (401)
                        .authenticationEntryPoint((request, response, authException) -> {
                            sendErrorResponse(response, 401, "Unauthenticated", "You need a valid token");
                        })
                        // Triggers when token is valid but Role is wrong (403)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            sendErrorResponse(response, 403, "Forbidden", "You do not have valid role to enter this endpoint");
                        })
                );
        return http.build();
    }
    private void sendErrorResponse(HttpServletResponse response, int status, String error, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = String.format(
                "{\"status\": %d, \"error\": \"%s\", \"message\": \"%s\"}",
                status, error, message
        );
        response.getWriter().write(json);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

