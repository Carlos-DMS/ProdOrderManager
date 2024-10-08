package com.AC.ProdOrderManager.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidField {
    private String fieldName;
    private String errorMessage;

    @Override
    public String toString() {
        return "Campo: " + fieldName + ", Erro: " + errorMessage;
    }
}
