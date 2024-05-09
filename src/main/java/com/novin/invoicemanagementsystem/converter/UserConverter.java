package com.novin.invoicemanagementsystem.converter;

import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto convertUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
