package com.AC.ProdOrderManager.dtos.prodOrder;

public record GetOrdersResponseDTO(String id, String costumer, String product, Integer quantity, String openingDate, String lastReviewDate, String deliveryDate, String status) {
}
