package com.data.tallermodelodatos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record ReservaCreateRequest(
        @NotNull(message = "Reservation date cannot be null")
        LocalDate fechaDeReserva,

        @NotNull(message = "Number of passengers cannot be null")
        @Positive(message = "Number of passengers must be positive")
        Integer numeroDePasajeros,

        @NotNull(message = "Client ID cannot be null")
        Long idCliente
) {
}
