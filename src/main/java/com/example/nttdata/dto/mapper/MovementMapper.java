package com.example.nttdata.dto.mapper;


import com.example.nttdata.dto.MovementDTO;
import com.example.nttdata.dto.ReportDTO;
import com.example.nttdata.models.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mappings({
            @Mapping(source = "account.numberAccount", target = "numeroCuenta"),
            @Mapping(source = "typeMovement", target = "tipoMovimiento"),
            @Mapping(source = "movementValue", target = "valor"),
            @Mapping(source = "movementBalance", target = "saldo"),
            @Mapping(source = "movementDate", target = "fechaMovimiento"),
            @Mapping(source = "account.client.dni", target = "clienteCedula"),
            @Mapping(source = "state", target = "estado")
    })
    MovementDTO toMovementDto(Movement movement);
    List<MovementDTO> toMovementDtos(List<Movement> movements);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Movement toMovement(MovementDTO movementDTO);
}
