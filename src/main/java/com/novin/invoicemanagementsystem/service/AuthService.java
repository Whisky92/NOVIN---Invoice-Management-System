package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.exception.UsernameAlreadyExistsException;
import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.model.UserCredentials;
import com.novin.invoicemanagementsystem.model.UserInput;

public interface AuthService {
    AuthResponse register(UserInput userInput) throws UsernameAlreadyExistsException;

    AuthResponse authenticate(UserCredentials credentials);
}
