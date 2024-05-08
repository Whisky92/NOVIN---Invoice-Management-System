package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.config.AuthResponse;
import com.novin.invoicemanagementsystem.entity.User;

public interface AuthService {
    AuthResponse register(User userData);

    AuthResponse authenticate(User userData);
}
