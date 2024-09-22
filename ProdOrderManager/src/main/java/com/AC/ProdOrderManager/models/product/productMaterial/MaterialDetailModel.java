package com.AC.ProdOrderManager.models.product.productMaterial;

import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.BaseMaterialModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class MaterialDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;
    @ManyToOne
    @JoinColumn(name = "base_material_name", referencedColumnName = "name")
    private BaseMaterialModel baseMaterial;
    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(precision = 10, scale = 2)
    private BigDecimal paidAmount;

    public BigDecimal getRemainingAmount() {
        if (totalAmount != null && paidAmount != null) {
            return totalAmount.subtract(paidAmount);
        }
        return BigDecimal.ZERO;
    }
}
