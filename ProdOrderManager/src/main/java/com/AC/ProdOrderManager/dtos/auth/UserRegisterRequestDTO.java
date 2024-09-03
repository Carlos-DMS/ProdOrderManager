package com.AC.ProdOrderManager.dtos.auth;

public record UserRegisterRequestDTO(String login, String password, String role) {
}
