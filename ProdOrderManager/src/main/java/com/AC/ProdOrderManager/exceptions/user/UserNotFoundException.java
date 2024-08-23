package com.AC.ProdOrderManager.exceptions.user;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }
}
