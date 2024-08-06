package com.example.user.controller;

import com.example.user.model.Logging;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Админ панель")
public class AdminController {
    private final UserService service;

    @GetMapping("/logs/{username}")
    @Operation(summary = "Получение логов пользователя")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Logging> getUserLogs(@PathVariable String username) {
        return service.getUserLogs(username);
    }

    @GetMapping("/period")
    @Operation(summary = "Получение всех логов за период")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Logging> getLogsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return service.getLogsByPeriod(start, end);
    }

    @GetMapping("/user-period")
    @Operation(summary = "Получение всех логов пользователя за период")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Logging> getLogsByUserAndPeriod(
            @RequestParam String username,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return service.getLogsByUserAndPeriod(username, start, end);
    }
}
