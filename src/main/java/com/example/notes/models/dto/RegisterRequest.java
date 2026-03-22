package com.example.notes.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Имя обязательно")
        String username,

        @NotBlank(message = "Пароль обязателен")
        @Size(min = 6, message = "Пароль должен быть минимум 6 символов")
        String password
) {
}
