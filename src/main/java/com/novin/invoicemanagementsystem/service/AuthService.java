package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserCredentials;

public interface AuthService {
    AuthResponse register(User userData);

    AuthResponse authenticate(UserCredentials credentials);
}
