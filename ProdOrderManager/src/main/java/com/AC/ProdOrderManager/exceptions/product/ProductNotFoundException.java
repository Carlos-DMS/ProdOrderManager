package com.AC.ProdOrderManager.exceptions.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Produto não encontrado.");
    }
}
