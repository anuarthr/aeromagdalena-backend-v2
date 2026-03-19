package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.NotBlank;

public record PasajeroCreateRequest(
        @NotBlank(message = "Passenger name cannot be blank")
        String nombre,

        @NotBlank(message = "Passenger last name cannot be blank")
        String apellido,

        @NotBlank(message = "Passport cannot be blank")
        String pasaporte,

        @NotBlank(message = "Nationality cannot be blank")
        String nacionalidad
) {
}
