package com.example.nttdata.dto.mapper;

import com.example.nttdata.dto.ReportDTO;
import com.example.nttdata.models.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mappings({
            @Mapping(source = "account.numberAccount",target = "numeroCuenta"),
            @Mapping(source = "typeMovement",target = "tipoMovimiento"),
            @Mapping(source = "account.balance",target = "saldoDisponible"),
            @Mapping(source = "movementBalance",target = "valor"),
            @Mapping(source = "movementDate",target = "fechaMovimiento"),
            @Mapping(source = "account.client.name",target = "nombreCliente"),
            @Mapping(source = "movementBalance",target = "saldoEnMovimiento"),
    })
    ReportDTO toReportDto(Movement movement);
    List<ReportDTO> toReportDtos(List<Movement> movements);

}
