package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.prodOrder.GetOrdersResponseDTO;
import com.AC.ProdOrderManager.dtos.prodOrder.OrderRegisterRequestDTO;
import com.AC.ProdOrderManager.services.ProdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodOrder")
public class ProdOrderController {
    @Autowired
    ProdOrderService prodOrderService;

    @PostMapping
    public ResponseEntity register(@RequestBody OrderRegisterRequestDTO body) {
        prodOrderService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/openOrders")
    public ResponseEntity<List<GetOrdersResponseDTO>> getOpenOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(prodOrderService.getAllOpenOrders());
    }

    @GetMapping("/completedOrders")
    public ResponseEntity<List<GetOrdersResponseDTO>> getCompletedOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(prodOrderService.getAllCompletedOrders());
    }
}
