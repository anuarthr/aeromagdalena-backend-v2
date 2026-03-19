package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.*;

/**
 * DTO de entrada para actualizar un Cliente
 */
public record ClienteUpdateRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @Email(message = "El email debe ser válido")
        @NotBlank(message = "El email es obligatorio")
        String email,

        @NotBlank(message = "El username es obligatorio")
        String username,

        String direccion,

        String telefono
) {
}
