package com.novin.invoicemanagementsystem.controller;

import com.novin.invoicemanagementsystem.model.RoleInput;
import com.novin.invoicemanagementsystem.model.UserOutput;
import com.novin.invoicemanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserOutput>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<UserOutput> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.deleteById(id));
    }

    @PutMapping("/{id}/setRoles")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> setRoles(@PathVariable Long id, @Valid @RequestBody RoleInput roleInput) {
        return ResponseEntity.ok().body(userService.setRoleOfUser(id, roleInput));
    }
}
