package com.AC.ProdOrderManager.models.prodOrder;

public enum ProdOrderStatus {
    SEPARANDO_MATERIAIS("Separando materiais"),
    EM_PRODUCAO("Em produção"),
    FINALIZADA("Finalizada");

    private final String statusReport;

    ProdOrderStatus(String statusReport) {
        this.statusReport = statusReport;
    }

    public String getStatusReport() {
        return statusReport;
    }
}
