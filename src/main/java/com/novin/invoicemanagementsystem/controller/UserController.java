package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.model.RoleInput;
import com.novin.invoicemanagementsystem.model.UserOutput;
import com.novin.invoicemanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public List<UserOutput> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public UserOutput deleteUserById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @PutMapping("/{id}/setRoles")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String setRoles(@PathVariable Long id, @Valid @RequestBody RoleInput roleInput) {
        return userService.setRoleOfUser(id, roleInput);
    }
}
