package com.novin.invoicemanagementsystem.service;

import com.novin.invoicemanagementsystem.entity.Token;
import com.novin.invoicemanagementsystem.entity.User;
import com.novin.invoicemanagementsystem.model.AuthResponse;
import com.novin.invoicemanagementsystem.model.UserCredentials;
import com.novin.invoicemanagementsystem.model.UserInput;
import com.novin.invoicemanagementsystem.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TokenRepository tokenRepository;
    private final UserService userService;

    private final AuthenticationManager authManager;
    private final JwtService jwtService;


    @Override
    public AuthResponse register(UserInput userInput) {

        if (userService.findUserByUsername(userInput.getUserName()).isPresent()) {
             return new AuthResponse(null, "A user with such username already exists");
        }

        User newUser = userService.createUser(userInput);

        newUser = userService.saveUser(newUser);
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
        User user = userService.findUserByUsername(credentials.getUserName()).orElseThrow();
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
