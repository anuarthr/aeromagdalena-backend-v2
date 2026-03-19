package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    // Cliente -> ClienteDto (salida)
    ClienteDto clienteToClienteDto(Cliente cliente);

    // ClienteCreateRequest -> Cliente (entrada para crear)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente clienteCreateRequestToCliente(ClienteCreateRequest request);

    // ClienteUpdateRequest -> Cliente (entrada para actualizar)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente clienteUpdateRequestToCliente(ClienteUpdateRequest request);
}