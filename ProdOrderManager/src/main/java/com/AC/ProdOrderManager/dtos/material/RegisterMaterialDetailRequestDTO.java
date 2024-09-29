package com.AC.ProdOrderManager.dtos.material;

import java.math.BigDecimal;

public record RegisterMaterialDetailRequestDTO(String baseMaterialName, Integer quantity, BigDecimal totalAmount, BigDecimal paidAmount) {
}
