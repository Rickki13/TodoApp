package com.example.user.service;

import com.example.user.dto.JwtAuthenticationResponse;
import com.example.user.dto.SignInRequest;
import com.example.user.dto.SignUpRequest;
import com.example.user.model.LogRequest;
import com.example.user.model.Role;
import com.example.user.model.User;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private RestTemplate restTemplate;
    private static final String LOG_SERVICE_URL =  "http://logging-service/logs";

    private void logAction(String username, String action, String details) {
        restTemplate.postForObject(LOG_SERVICE_URL, new LogRequest(username, action, details), Void.class);
    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .surname(request.getSurname())
                .lastname(request.getLastname())
                .role(Role.ROLE_USER)
                .build();

        userService.register(user);
        var jwt = jwtService.generateToken(user);
        logAction(request.getLogin(), "SignUP", "SignUp user with username: " + request.getLogin());
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken(user);
//        logAction(request.getLogin(), "SignIN", "SignIn user with username: " + request.getLogin());
        return new JwtAuthenticationResponse(jwt);

    }
}
