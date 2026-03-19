package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.NotBlank;

public record AeropuertoUpdateRequest(
        @NotBlank(message = "Airport name cannot be blank")
        String nombre,

        @NotBlank(message = "City cannot be blank")
        String ciudad,

        @NotBlank(message = "Country cannot be blank")
        String pais
) {
}
