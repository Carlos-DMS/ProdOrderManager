package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.auth.LoginRequestDTO;
import com.AC.ProdOrderManager.dtos.auth.LoginResponseDTO;
import com.AC.ProdOrderManager.dtos.auth.RegisterRequestDTO;
import com.AC.ProdOrderManager.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody @Valid LoginRequestDTO body) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.login(body));
        }
        catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDTO body) {
        if (authService.register(body)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO: O login utilizado já está cadastrado.");
    }
}
