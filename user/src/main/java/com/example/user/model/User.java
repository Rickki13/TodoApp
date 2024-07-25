package com.example.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id @GeneratedValue
    private Long id;
    @NonNull @NotBlank
    private String firstname;
    @NonNull @NotBlank
    private String surname;
    @NonNull @NotBlank
    private String lastname;
    @NonNull @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;
    @NonNull @NotBlank
    private String login;
    @NonNull @NotBlank
    private String password;
}
