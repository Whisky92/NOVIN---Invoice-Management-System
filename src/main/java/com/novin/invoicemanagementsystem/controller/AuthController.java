package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.config.AuthResponse;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User userData) {
        return ResponseEntity.ok(authService.register(userData));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody User userData) {
        return ResponseEntity.ok(authService.authenticate(userData));
    }
}
