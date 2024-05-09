package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.converter.Converter;
import com.novin.invoicemanagementsystem.entity.Role;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.RoleInput;
import com.novin.invoicemanagementsystem.model.UserInput;
import com.novin.invoicemanagementsystem.model.UserOutput;
import com.novin.invoicemanagementsystem.repository.RoleRepository;
import com.novin.invoicemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User createUser(UserInput userInput) {
        return User.builder()
                .firstName(userInput.getFirstName())
                .lastName(userInput.getLastName())
                .userName(userInput.getUserName())
                .password(passwordEncoder.encode(userInput.getPassword()))
                .roles(converter.convertRoleNamesToRoles(userInput.getRoles()))
                .build();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserOutput> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(converter::convertUserToUserOutput)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutput deleteById(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with such name does not exits"));

        userRepository.delete(userToDelete);
        return converter.convertUserToUserOutput(userToDelete);
    }

    @Override
    public String setRoleOfUser(Long id, RoleInput roleInput) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User with such name does not exits"));
        List<Role> roles = converter.convertRoleNamesToRoles(roleInput);
        user.setRoles(roles);
        userRepository.save(user);

        return "Rules have been successfully updated";
    }
}
