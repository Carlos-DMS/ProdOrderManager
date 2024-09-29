package com.AC.ProdOrderManager.dtos.product;

import com.AC.ProdOrderManager.dtos.material.RegisterMaterialDetailRequestDTO;

import java.util.Set;

public record RegisterProductRequestDTO(String id, String name, String productType, Set<RegisterMaterialDetailRequestDTO> productMaterials){
}
