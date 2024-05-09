package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.entity.Token;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.UserCredentials;
import com.novin.invoicemanagementsystem.repository.TokenRepository;
import com.novin.invoicemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(User userData) {

        System.out.println("megyen:)");
        System.out.println("userData: " + userData);

        if (userRepository.findByUserName(userData.getUsername()).isPresent()) {
             return new AuthResponse(null, "A user with such username already exists");
        }

        User newUser = User.builder()
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .userName(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .roles(userData.getRoles())
                .build();

        newUser = userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(newUser);
        saveUserToken(jwtToken, newUser);

        return new AuthResponse(jwtToken, "User registration was successful");
    }

    @Override
    public AuthResponse authenticate(UserCredentials credentials) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUserName(),
                        credentials.getPassword()
                ));
        User user = userRepository.findByUserName(credentials.getUserName()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwtToken, user);

        return new AuthResponse(jwtToken, "User login was successful");
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t ->
            t.setLoggedOut(true)
        );

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = Token.builder()
                .token(jwt)
                .loggedOut(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }
}
