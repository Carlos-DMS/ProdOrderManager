package com.AC.ProdOrderManager.controllers;

import com.AC.ProdOrderManager.dtos.material.RegisterBaseMaterialRequestDTO;
import com.AC.ProdOrderManager.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping("/registerBaseMaterial")
    public ResponseEntity registerBaseMaterial (@RequestBody RegisterBaseMaterialRequestDTO body) {
        materialService.registerBaseMaterial(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
