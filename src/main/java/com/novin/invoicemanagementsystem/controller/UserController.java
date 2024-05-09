package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserDto;
import com.novin.invoicemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public UserDto deleteUserById(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
