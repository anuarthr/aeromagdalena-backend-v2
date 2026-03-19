package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AerolineaCreateRequest(
        @NotBlank(message = "Airline name cannot be blank")
        String nombre,

        @NotNull(message = "Airline code cannot be null")
        Long codigoAerolinea,

        @NotBlank(message = "Country of origin cannot be blank")
        String paisDeOrigen
) {
}
