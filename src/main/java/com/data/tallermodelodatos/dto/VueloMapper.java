package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VueloMapper {
    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    // Vuelo -> VueloDto (salida)
    @Mapping(source = "aerolinea.idAerolinea", target = "aerolineaId")
    @Mapping(source = "aeropuerto.idAeropuerto", target = "aeropuertoId")
    VueloDto vueloToVueloDto(Vuelo vuelo);

    // VueloCreateRequest -> Vuelo (entrada para crear)
    @Mapping(target = "idVuelo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "aerolinea", ignore = true)
    @Mapping(target = "aeropuerto", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Vuelo vueloCreateRequestToVuelo(VueloCreateRequest request);

    // VueloUpdateRequest -> Vuelo (entrada para actualizar)
    @Mapping(target = "idVuelo", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "aerolinea", ignore = true)
    @Mapping(target = "aeropuerto", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Vuelo vueloUpdateRequestToVuelo(VueloUpdateRequest request);
}