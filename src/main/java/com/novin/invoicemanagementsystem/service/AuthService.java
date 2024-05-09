package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.model.UserCredentials;
import com.novin.invoicemanagementsystem.model.UserInput;

public interface AuthService {
    AuthResponse register(UserInput userInput);

    AuthResponse authenticate(UserCredentials credentials);
}
