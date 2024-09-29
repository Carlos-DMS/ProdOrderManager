package com.AC.ProdOrderManager.dtos.prodOrder;

public record RegisterOrderRequestDTO(String customer, String productIdentifier, Integer quantity, String deliveryDate) {
}
