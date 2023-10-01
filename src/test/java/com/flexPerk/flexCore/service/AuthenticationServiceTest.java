package com.flexPerk.flexCore.service;

import com.flexPerk.flexCore.auth.AuthenticationRequest;
import com.flexPerk.flexCore.auth.AuthenticationResponse;
import com.flexPerk.flexCore.model.RegisterRequest;
import com.flexPerk.flexCore.model.Role;
import com.flexPerk.flexCore.model.User;
import com.flexPerk.flexCore.repository.TokenRepository;
import com.flexPerk.flexCore.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    UserRepository userRepository;

    @Mock
    TokenRepository tokenRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtService jwtService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest("testuser", "testpass",
                Role.ADMIN);
        User user = User.builder()
                .username("testuser")
                .password("testpass")
                .role(Role.ADMIN)
                .build();

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedpassword");
        when(userRepository.save(any())).thenReturn(user);
        when(jwtService.generateToken(any())).thenReturn("jwtToken");
        when(jwtService.generateRefreshToken(any())).thenReturn("refreshToken");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("jwtToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());
    }


}
