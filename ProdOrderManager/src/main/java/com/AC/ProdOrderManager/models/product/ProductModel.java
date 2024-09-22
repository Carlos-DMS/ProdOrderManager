package com.AC.ProdOrderManager.models.product;

import com.AC.ProdOrderManager.models.product.productMaterial.MaterialDetailModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "code")
public class ProductModel {
    @Id
    private String code;
    @Column(unique = true)
    private String name;
    private ProductType productType;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MaterialDetailModel> productMaterials = new HashSet<>();
}
