package com.example.nttdata.dto.mapper;

import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.models.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "dni",target = "cedula"),
            @Mapping(source = "name",target = "nombre"),
            @Mapping(source = "genre",target = "genero"),
            @Mapping(source = "age",target = "edad"),
            @Mapping(source = "direction",target = "direccion"),
            @Mapping(source = "phone",target = "telefono"),
            @Mapping(source = "username",target = "usuario"),
            @Mapping(source = "password",target = "contrasenia"),
            @Mapping(source = "state",target = "estado")
    })
    ClientDTO toClientDto(Client client);
    List<ClientDTO> toClientDtos(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toClient(ClientDTO clientDTO);
    List<Client> toClients(List<ClientDTO> clientDTOS);
}
