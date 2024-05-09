package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.RoleInput;
import com.novin.invoicemanagementsystem.model.UserInput;
import com.novin.invoicemanagementsystem.model.UserOutput;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserInput userInput);

    Optional<User> findUserByUsername(String username);

    User saveUser(User user);

    List<UserOutput> getAllUsers();

    UserOutput deleteById(Long id);

    String setRoleOfUser(Long id, RoleInput roleInput);
}
