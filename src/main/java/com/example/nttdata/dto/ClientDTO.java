package com.example.nttdata.dto;

import com.example.nttdata.util.enums.GenreEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@JsonDeserialize(builder = ClientDTO.ClientDTOBuilder.class)
public class ClientDTO {


    private String nombre;
    private String cedula;
    private GenreEnum genero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private String usuario;
    private String contrasenia;
    private Boolean estado;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClientDTOBuilder{}
}
