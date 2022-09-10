package com.example.nttdata.util.enums;

public enum TypeMovementEnum {

    DEPOSITO("deposito"),
    RETIRO("retiro");

    private String typeMovement;

    TypeMovementEnum(String typeMovement) {
        this.typeMovement = typeMovement;
    }

    public String getTypeMovement() {
        return typeMovement;
    }

    public void setTypeMovement(String typeMovement) {
        this.typeMovement = typeMovement;
    }
}
