package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.converter.UserConverter;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserDto;
import com.novin.invoicemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto deleteById(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with such name does not exits"));

        userRepository.delete(userToDelete);
        return userConverter.convertUserToUserDto(userToDelete);
    }
}
