package com.AC.ProdOrderManager.repositories;

import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.BaseMaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseMaterialRepository extends JpaRepository<BaseMaterialModel, String> {

}