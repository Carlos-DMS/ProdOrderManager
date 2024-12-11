package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.material.BaseMaterialNotFoundException;
import com.AC.ProdOrderManager.exceptions.product.ProductNotFoundException;
import com.AC.ProdOrderManager.exceptions.user.UserAlreadyExistsException;
import com.AC.ProdOrderManager.exceptions.material.BaseMaterialAlreadyExistsException;
import com.AC.ProdOrderManager.exceptions.prodOrder.NoMatchingOrdersException;
import com.AC.ProdOrderManager.exceptions.user.InvalidLoginCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //USER EXCEPTIONS

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    private ResponseEntity<String> userNotFoundHandler(InvalidLoginCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> userAlreadyExistsHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    //PROD. ORDER EXCEPTIONS

    @ExceptionHandler(NoMatchingOrdersException.class)
    private ResponseEntity<String> noMatchingOrdersHandler(NoMatchingOrdersException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
    }

    //PRODUCT EXCEPTIONS

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<String> productNotFoundHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
    }

    //MATERIAL EXCEPTIONS

    @ExceptionHandler(BaseMaterialAlreadyExistsException.class)
    private ResponseEntity<String> baseMaterialAlreadyExistsHandler(BaseMaterialAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(BaseMaterialNotFoundException.class)
    private ResponseEntity<String> baseMaterialNotFoundHandler(BaseMaterialNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
    }

    //OTHER EXCEPTIONS

    @ExceptionHandler(InvalidDataException.class)
    private ResponseEntity<String> invalidDataHandler(InvalidDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    private ResponseEntity<String> invalidDateFormatHandler(DateTimeParseException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de data inválido");
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> genericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + exception.getMessage());
    }
}
