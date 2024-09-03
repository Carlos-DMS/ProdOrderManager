package com.AC.ProdOrderManager.exceptions;

public class InvalidDateFormatException extends Exception{
    public InvalidDateFormatException() {
        super("Formato de data inválido.");
    }
}
