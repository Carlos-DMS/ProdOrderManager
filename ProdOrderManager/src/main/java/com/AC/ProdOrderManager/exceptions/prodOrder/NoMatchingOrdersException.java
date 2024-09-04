package com.AC.ProdOrderManager.exceptions.prodOrder;

public class NoMatchingOrdersException extends RuntimeException{
    public NoMatchingOrdersException(String message) {
        super(message);
    }
}
