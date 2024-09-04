package com.AC.ProdOrderManager.dtos.prodOrder;

public record OrderRegisterRequestDTO(String customer, String product, Integer quantity, String deliveryDate) {
}
