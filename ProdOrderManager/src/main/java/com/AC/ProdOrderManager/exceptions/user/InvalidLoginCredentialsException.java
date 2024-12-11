package com.AC.ProdOrderManager.exceptions.user;

public class InvalidLoginCredentialsException extends  RuntimeException{
    public InvalidLoginCredentialsException() {
        super("Credenciais inválidas. Verifique o nome de usuário e a senha.");
    }
}
