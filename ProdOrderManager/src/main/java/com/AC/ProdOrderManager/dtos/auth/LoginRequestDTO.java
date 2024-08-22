package com.AC.ProdOrderManager.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String login, @NotBlank String password) {
}
