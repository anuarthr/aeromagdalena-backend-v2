package com.data.tallermodelodatos.exception;

public class AerolineaNotFoundException extends ResourceNotFoundException {
    public AerolineaNotFoundException(String message) {
        super(message);
    }

    public AerolineaNotFoundException() {
        super("Aerolínea no encontrada");
    }
}
