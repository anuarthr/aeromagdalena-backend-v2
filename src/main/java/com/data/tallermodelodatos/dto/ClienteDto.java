package com.data.tallermodelodatos.dto;

import java.time.LocalDateTime;

/**
 * DTO de salida para Cliente (lectura)
 */
public record ClienteDto(
        Long idCliente,
        String nombre,
        String apellido,
        String email,
        String username,
        String direccion,
        String telefono,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}