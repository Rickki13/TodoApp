package com.example.task.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Long id;
    private String firstname;
    private String surname;
    private String lastname;
    private Role role;
    private String username;
    private String password;

}
