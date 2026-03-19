package com.data.tallermodelodatos.exception;

public class AeropuertoNotFoundException extends ResourceNotFoundException {
    public AeropuertoNotFoundException(String message) {
        super(message);
    }

    public AeropuertoNotFoundException() {
        super("Aeropuerto no encontrado");
    }
}
