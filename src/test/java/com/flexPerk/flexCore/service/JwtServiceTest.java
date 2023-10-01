package com.flexPerk.flexCore.service;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(userDetails.getUsername()).thenReturn("testUser");
        when(userDetails.getPassword()).thenReturn("password");
    }

    @Test
    void generateToken() {
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));
    }

    @Test
    void testGenerateTokenWithExtraClaims() {
        String token = jwtService.generateToken(Collections.emptyMap(), userDetails);
        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));
    }

    @Test
    void generateRefreshToken() {
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        assertNotNull(refreshToken);
        assertTrue(refreshToken.startsWith("eyJhbGciOiJIUzI1NiJ9"));
    }

}
