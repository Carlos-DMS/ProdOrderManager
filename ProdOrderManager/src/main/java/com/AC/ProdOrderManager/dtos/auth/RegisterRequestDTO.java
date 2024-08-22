package com.AC.ProdOrderManager.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(@NotBlank String login, @NotBlank String password, @NotBlank String role) {
}
