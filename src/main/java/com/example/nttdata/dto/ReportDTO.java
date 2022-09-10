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
@JsonDeserialize(builder = ReportDTO.ReportDTOBuilder.class)
public class ReportDTO {

    private String numeroCuenta;
    private TypeMovementEnum tipoMovimiento;
    private Double saldoDisponible;
    private Double valor;
    @JsonFormat(pattern="yyy-MM-dd ")
    private Date fechaMovimiento;
    private String nombreCliente;
    private Double saldoEnMovimiento;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ReportDTOBuilder{
    }
}
