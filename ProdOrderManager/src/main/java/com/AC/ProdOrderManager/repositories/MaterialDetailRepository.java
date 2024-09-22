package com.AC.ProdOrderManager.repositories;

import com.AC.ProdOrderManager.models.product.productMaterial.MaterialDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialDetailRepository extends JpaRepository<MaterialDetailModel, UUID> {

}