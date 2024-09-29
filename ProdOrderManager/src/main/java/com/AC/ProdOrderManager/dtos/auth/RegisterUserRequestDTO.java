package com.AC.ProdOrderManager.dtos.auth;

public record RegisterUserRequestDTO(String login, String password, String role) {
}
