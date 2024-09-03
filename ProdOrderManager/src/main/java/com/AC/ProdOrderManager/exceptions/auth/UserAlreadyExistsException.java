package com.AC.ProdOrderManager.exceptions.auth;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("O usuário já está cadastrado.");
    }
}
