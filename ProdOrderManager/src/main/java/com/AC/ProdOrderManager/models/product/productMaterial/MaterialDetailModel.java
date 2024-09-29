package com.AC.ProdOrderManager.models.product.productMaterial;

import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.BaseMaterialModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "material_details")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class MaterialDetailModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductModel product;
    @ManyToOne
    @JoinColumn(name = "base_material_name", referencedColumnName = "name")
    private BaseMaterialModel baseMaterial;
    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(precision = 10, scale = 2)
    private BigDecimal paidAmount;

    public MaterialDetailModel(ProductModel product, BaseMaterialModel baseMaterial, Integer quantity, BigDecimal totalAmount, BigDecimal paidAmount) {
        this.product = product;
        this.baseMaterial = baseMaterial;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRemainingAmount() {
        if (totalAmount != null && paidAmount != null) {
            return totalAmount.subtract(paidAmount);
        }
        return BigDecimal.ZERO;
    }
}
