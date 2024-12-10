package com.AC.ProdOrderManager.exceptions.material;

public class BaseMaterialNotFoundException extends RuntimeException{
    public BaseMaterialNotFoundException() {
        super("Material não encontrado.");
    }
}
