package com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial;

public enum UnitType {
    PC("PÇ"),
    KG("KG"),
    MT("MT"),
    LT("LT"),
    PAR("PAR");

    private final String unitTypeReport;

    UnitType(String unitType) {
        this.unitTypeReport = unitType;
    }

    public String getUnitTypeReport() {
        return unitTypeReport;
    }
}
