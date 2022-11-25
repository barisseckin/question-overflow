package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.TokenDto;
import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.dto.request.UserLoginRequest;
import com.developertools.questionoverflow.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService,
                       TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenDto login(UserLoginRequest request) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );

            UserDto userDto = userService.getUserByUsername(request.getUsername());

            return new TokenDto(
                    tokenGenerator.generateToken(auth),
                    userDto
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
