package com.AC.ProdOrderManager.models.product;

public enum ProductType {
    TRANSFORMADOR("Transformador"),
    FONTE("Fonte"),
    INDUTOR("Indutor");

    private final String productTypeReport;

    ProductType(String productType) {
        this.productTypeReport = productType;
    }

    public String getProductTypeReport() {
        return productTypeReport;
    }
}
