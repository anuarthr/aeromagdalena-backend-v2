package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * DTO de entrada para actualizar un Vuelo
 */
public record VueloUpdateRequest(
        @NotBlank(message = "El origen es obligatorio")
        String origen,

        @NotBlank(message = "El destino es obligatorio")
        String destino,

        @NotNull(message = "La fecha de salida es obligatoria")
        LocalDateTime fechaDeSalida,

        @NotNull(message = "La duración es obligatoria")
        @Positive(message = "La duración debe ser mayor a 0")
        Integer duracion,

        @NotNull(message = "La capacidad es obligatoria")
        @Positive(message = "La capacidad debe ser mayor a 0")
        Integer capacidad,

        @NotNull(message = "La aerolínea es obligatoria")
        Long aerolineaId,

        @NotNull(message = "El aeropuerto es obligatorio")
        Long aeropuertoId
) {
}
