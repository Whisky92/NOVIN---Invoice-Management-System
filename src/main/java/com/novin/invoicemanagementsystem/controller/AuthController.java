package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.exception.UsernameAlreadyExistsException;
import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.model.UserCredentials;
import com.novin.invoicemanagementsystem.model.UserInput;
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
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserInput userInput) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok().body(authService.register(userInput));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok().body(authService.authenticate(userCredentials));
    }
}
