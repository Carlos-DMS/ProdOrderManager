package com.AC.ProdOrderManager.models.product;

import com.AC.ProdOrderManager.models.product.productMaterial.MaterialDetailModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Column(unique = true)
    private String name;
    private ProductType productType;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MaterialDetailModel> productMaterials = new ArrayList<>();

    public ProductModel(String id, String name, ProductType productType) {
        this.id = id;
        this.name = name;
        this.productType = productType;
    }
}

