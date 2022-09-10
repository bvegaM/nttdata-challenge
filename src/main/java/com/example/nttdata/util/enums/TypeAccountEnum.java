package com.example.nttdata.util.enums;

public enum TypeAccountEnum {

    AHORRO("ahorros"),
    CORRIENTE("corriente");

    private String typeAccount;

    TypeAccountEnum(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }
}
