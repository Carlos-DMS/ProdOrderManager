package com.AC.ProdOrderManager.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("O usuário já está cadastrado.");
    }
}
