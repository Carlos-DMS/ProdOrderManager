package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.auth.LoginRequestDTO;
import com.AC.ProdOrderManager.dtos.auth.LoginResponseDTO;
import com.AC.ProdOrderManager.dtos.auth.UserRegisterRequestDTO;
import com.AC.ProdOrderManager.services.AuthService;
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

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO body) {
        authService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(body));
    }
}
