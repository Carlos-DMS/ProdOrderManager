package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.exceptions.auth.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.auth.InvalidPasswordException;
import com.AC.ProdOrderManager.exceptions.auth.UserAlreadyExistsException;
import com.AC.ProdOrderManager.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    private ResponseEntity<String> invalidDataHandler(InvalidDataException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<String> invalidPasswordHandler(InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> userAlreadyExistsHandler(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
