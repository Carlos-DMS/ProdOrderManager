package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.prodOrder.OrderRegisterRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDateFormatException;
import com.AC.ProdOrderManager.services.ProdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodOrder")
public class ProdOrderController {
    @Autowired
    ProdOrderService prodOrderService;

    @PostMapping
    public ResponseEntity register (OrderRegisterRequestDTO body) throws InvalidDateFormatException {
        prodOrderService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
