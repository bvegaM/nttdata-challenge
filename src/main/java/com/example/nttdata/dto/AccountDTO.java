package com.example.nttdata.dto;

import com.example.nttdata.util.enums.TypeAccountEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = AccountDTO.AccountDTOBuilder.class)
public class AccountDTO {

    private String numeroCuenta;
    private TypeAccountEnum tipoCuenta;
    private Double saldo;
    private String clienteCedula;
    private Boolean estado;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountDTOBuilder{ }

}
