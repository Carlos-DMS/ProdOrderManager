package com.AC.ProdOrderManager.dtos.prodOrder;

import com.AC.ProdOrderManager.models.product.ProductModel;

public record GetOrdersResponseDTO(String id, String costumer, ProductModel product, Integer quantity, String openingDate, String lastReviewDate, String deliveryDate, String status) {
}
