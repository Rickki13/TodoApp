package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {
    private final UserService service;

    @Operation(summary = "Получение пользователя")
    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
       return service.getByUsername(username);
    }

}
