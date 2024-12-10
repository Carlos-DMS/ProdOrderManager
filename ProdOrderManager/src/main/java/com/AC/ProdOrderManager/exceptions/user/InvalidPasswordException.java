package com.AC.ProdOrderManager.exceptions.user;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}
