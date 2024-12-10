package com.AC.ProdOrderManager.exceptions.material;

public class BaseMaterialAlreadyExistsException extends RuntimeException{
    public BaseMaterialAlreadyExistsException() {
        super("Material já está cadastrado.");
    }
}
