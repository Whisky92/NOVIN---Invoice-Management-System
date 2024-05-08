package com.novin.invoicemanagementsystem.config;

import com.novin.invoicemanagementsystem.entity.Token;
import com.novin.invoicemanagementsystem.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class LogoutHandlerImpl implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public LogoutHandlerImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        tokenRepository.findByToken(token).ifPresent((myToken) -> {
            myToken.setLoggedOut(true);
            tokenRepository.save(myToken);
        });
    }
}
