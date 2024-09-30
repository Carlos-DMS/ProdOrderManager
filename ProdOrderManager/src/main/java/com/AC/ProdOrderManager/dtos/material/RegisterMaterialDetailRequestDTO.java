package com.AC.ProdOrderManager.dtos.material;

import java.math.BigDecimal;

public record RegisterMaterialDetailRequestDTO(String baseMaterialName, BigDecimal quantity, BigDecimal totalAmount, BigDecimal paidAmount) {
}
