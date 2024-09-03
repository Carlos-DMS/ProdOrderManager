package com.AC.ProdOrderManager.dtos.prodOrder;

public record OrderRegisterRequestDTO(String costumer, String product, Integer quantity, String deliveryDate) {
}
