package com.AC.ProdOrderManager.models.user;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private final String roleReport;

    UserRole(String role) {
        this.roleReport = role;
    }

    public String getRoleReport() {
        return roleReport;
    }
}
