package com.example.nttdata.dto;

import com.example.nttdata.util.enums.TypeMovementEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonDeserialize(builder = MovementDTO.MovementDTOBuilder.class)
public class MovementDTO {

    private String numeroCuenta;
    private TypeMovementEnum tipoMovimiento;
    private Double saldo;
    private Double valor;
    @JsonFormat(pattern="yyy-MM-dd ")
    private Date fechaMovimiento;
    private String clienteCedula;
    private Boolean estado;
    @JsonPOJOBuilder(withPrefix = "")
    public static class MovementDTOBuilder{

    }
}
