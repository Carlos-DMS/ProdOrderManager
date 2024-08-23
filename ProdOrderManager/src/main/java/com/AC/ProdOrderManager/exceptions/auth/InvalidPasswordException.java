package com.AC.ProdOrderManager.exceptions.auth;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}
