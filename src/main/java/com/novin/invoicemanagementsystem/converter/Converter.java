package com.novin.invoicemanagementsystem.converter;

import com.novin.invoicemanagementsystem.entity.Role;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.RoleInput;
import com.novin.invoicemanagementsystem.model.UserOutput;
import com.novin.invoicemanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {
     private final RoleRepository roleRepository;

    public UserOutput convertUserToUserOutput(User user) {
        return UserOutput.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public List<Role> convertRoleNamesToRoles(RoleInput roleInput) {
        List roles = roleInput.getRoles().stream()
                .map(roleName -> {
                    Role role = roleRepository.findByName(roleName);

                    if (role == null) {
                        throw new RuntimeException("Role with such name does not exits");
                    }
                    return role;
                }).collect(Collectors.toList());
        return roles;
    }
}
