package com.data.tallermodelodatos.exception;

public class VueloNotFoundException extends ResourceNotFoundException {
    public VueloNotFoundException(String message) {
        super(message);
    }

    public VueloNotFoundException() {
        super("Vuelo no encontrado");
    }
}
