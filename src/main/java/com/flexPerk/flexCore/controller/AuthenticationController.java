package com.flexPerk.flexCore.controller;

import com.flexPerk.flexCore.auth.AuthenticationRequest;
import com.flexPerk.flexCore.auth.AuthenticationResponse;
import com.flexPerk.flexCore.exception.EntityAlreadyExistsException;
import com.flexPerk.flexCore.model.RegisterRequest;
import com.flexPerk.flexCore.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Handles authentication requests, including registration, login, and token refresh.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Registers a new user.
     *
     * @param request the registration request containing user information.
     * @return a response containing the JWT for the registered user.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse = service.register(request);
        if (authenticationResponse != null) {
            return ResponseEntity.ok(authenticationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed as " + request.getUsername() + " already exists");
        }

    }

    /**
     * Authenticates a user and returns a JWT.
     *
     * @param request the authentication request containing username and password.
     * @return a response containing the JWT for the authenticated user.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    /**
     * Refreshes the JWT token.
     *
     * @param request  the HTTP request containing the current JWT.
     * @param response the HTTP response where the new JWT will be attached.
     * @throws IOException if an input or output exception occurred.
     */
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
