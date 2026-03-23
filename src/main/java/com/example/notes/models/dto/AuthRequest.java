package com.example.notes.models.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "Username обязателен")
        String username,
        @NotBlank(message = "Пароль обязателен")
        String password
) {
}
