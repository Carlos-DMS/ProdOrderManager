package com.AC.ProdOrderManager.models.product;

import lombok.Getter;

@Getter
public enum ProductType {
    TRANSFORMADOR("Transformador", 10),
    INDUTOR("Indutor", 10),
    FONTE("Fonte", 30);


    private final String productTypeReport;
    private final Integer idPrefix;

    ProductType(String productType, Integer idPrefix) {
        this.productTypeReport = productType;
        this.idPrefix = idPrefix;
    }
}
