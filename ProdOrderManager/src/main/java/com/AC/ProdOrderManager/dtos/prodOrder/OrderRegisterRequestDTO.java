package com.AC.ProdOrderManager.dtos.prodOrder;

import com.AC.ProdOrderManager.models.product.ProductModel;

public record OrderRegisterRequestDTO(String customer, ProductModel product, Integer quantity, String deliveryDate) {
}
