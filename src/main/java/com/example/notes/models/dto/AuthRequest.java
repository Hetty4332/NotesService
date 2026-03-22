package com.example.notes.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Имя обязательно")
    String username;

    @NotBlank(message = "Пароль обязателен")
    String password;
}
