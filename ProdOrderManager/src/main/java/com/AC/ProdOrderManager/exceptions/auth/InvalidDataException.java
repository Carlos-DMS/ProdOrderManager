package com.AC.ProdOrderManager.exceptions.auth;

import java.util.List;

public class InvalidDataException extends RuntimeException {
    private List<InvalidField> invalidFields;

    public InvalidDataException(List<InvalidField> invalidFields) {
        super("Um ou mais dados de registro são inválidos.");
        this.invalidFields = invalidFields;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        message.append("\nCampos:");
        for (InvalidField field : invalidFields) {
            message.append("\n").append(field);
        }
        return message.toString();
    }
}

