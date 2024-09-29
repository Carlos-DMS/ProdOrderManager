package com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "base_materials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class BaseMaterialModel {
    @Id
    private String name;
    private UnitType unitType;
}
