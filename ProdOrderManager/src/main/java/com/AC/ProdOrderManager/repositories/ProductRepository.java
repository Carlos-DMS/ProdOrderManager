package com.AC.ProdOrderManager.repositories;

import com.AC.ProdOrderManager.models.product.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, String> {

}