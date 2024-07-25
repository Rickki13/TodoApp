package com.example.user.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String login;
    private String password;
}
