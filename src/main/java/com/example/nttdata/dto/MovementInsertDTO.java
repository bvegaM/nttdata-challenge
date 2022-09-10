package com.example.nttdata.dto;

import com.example.nttdata.util.enums.TypeMovementEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = MovementInsertDTO.MovementInsertDTOBuilder.class)
public class MovementInsertDTO {

    private String numeroCuenta;
    private Double valor;
    private TypeMovementEnum tipoMovimiento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MovementInsertDTOBuilder{

    }
}
