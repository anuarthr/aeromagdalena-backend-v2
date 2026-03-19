package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.PasajeroCreateRequest;
import com.data.tallermodelodatos.dto.PasajeroDto;
import com.data.tallermodelodatos.dto.PasajeroUpdateRequest;
import com.data.tallermodelodatos.exception.PasajeroNotFoundException;
import com.data.tallermodelodatos.services.PasajeroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajeros")
@CrossOrigin(origins = "http://localhost:5173")

public class PasajeroController {

    private final PasajeroService pasajeroService;


    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping()
    public ResponseEntity<List<PasajeroDto>> getAllPasajeros() {
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasajeroDto> getPasajeroById(@PathVariable Long id) {
        return pasajeroService.buscarPasajeroPorId(id)
                .map(pasajero -> ResponseEntity.ok().body(pasajero))
                .orElseThrow(PasajeroNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<PasajeroDto> createPasajero(@Valid @RequestBody PasajeroCreateRequest pasajeroRequest) throws URISyntaxException {
        PasajeroDto pasajeroDto = new PasajeroDto(null, pasajeroRequest.nombre(), pasajeroRequest.apellido(), pasajeroRequest.pasaporte(), pasajeroRequest.nacionalidad());
        return crearNuevoPasajero(pasajeroDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasajeroDto> actualizarPasajero(@PathVariable Long id, @Valid @RequestBody PasajeroUpdateRequest pasajeroRequest) {
        PasajeroDto pasajeroDto = new PasajeroDto(null, pasajeroRequest.nombre(), pasajeroRequest.apellido(), pasajeroRequest.pasaporte(), pasajeroRequest.nacionalidad());
        Optional<PasajeroDto> pasajeroUpdate = pasajeroService.actualizarPasajero(id, pasajeroDto);
        return pasajeroUpdate.map(pasajero -> ResponseEntity.ok(pasajero))
                .orElseGet(() -> {
                    return crearNuevoPasajero(pasajeroDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasajero(@PathVariable Long id) {
        pasajeroService.eliminarPasajero(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<PasajeroDto> crearNuevoPasajero(PasajeroDto pasajero) {
        PasajeroDto nuevoPasajero = pasajeroService.guardarPasajero(pasajero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoPasajero.idPasajero())
                .toUri();
        return ResponseEntity.created(location).body(nuevoPasajero);
    }
}
