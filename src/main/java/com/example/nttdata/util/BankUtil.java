package com.example.nttdata.util;

import com.example.nttdata.util.enums.TypeMovementEnum;

public class BankUtil {

    public static Double verifyValueTypeMovement(TypeMovementEnum typeMovementEnum, Double value){
        switch (typeMovementEnum){
            case RETIRO:
                return Math.abs(value) * -1;
            case DEPOSITO:
                return Math.abs(value);
            default:
                return value;
        }
    }
}
