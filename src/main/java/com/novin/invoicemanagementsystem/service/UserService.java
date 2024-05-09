package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto deleteById(Long id);
}
