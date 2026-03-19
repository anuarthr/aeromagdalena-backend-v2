package com.data.tallermodelodatos.dto;

import java.time.LocalDateTime;

/**
 * DTO de salida para Vuelo (lectura)
 */
public record VueloDto(
        Long idVuelo,
        String origen,
        String destino,
        LocalDateTime fechaDeSalida,
        Integer duracion,
        Integer capacidad,
        Long aerolineaId,
        Long aeropuertoId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}