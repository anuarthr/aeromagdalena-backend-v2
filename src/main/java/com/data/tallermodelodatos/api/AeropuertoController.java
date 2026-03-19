package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.AeropuertoCreateRequest;
import com.data.tallermodelodatos.dto.AeropuertoDto;
import com.data.tallermodelodatos.dto.AeropuertoUpdateRequest;
import com.data.tallermodelodatos.services.AeropuertoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuertos")
@CrossOrigin(origins = "http://localhost:5173")
public class AeropuertoController {

    private final AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping()
    public ResponseEntity<List<AeropuertoDto>> getAllAeropuertos() {
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AeropuertoDto> getAeropuertoById(@PathVariable Long id) {
        return aeropuertoService.buscarAeropuertoPorId(id)
                .map(aeropuerto -> ResponseEntity.ok().body(aeropuerto))
                .orElseThrow(() -> new RuntimeException("Aeropuerto not found"));
    }

    @PostMapping()
    public ResponseEntity<AeropuertoDto> createAeropuerto(@Valid @RequestBody AeropuertoCreateRequest aeropuertoRequest) throws URISyntaxException {
        AeropuertoDto aeropuertoDto = new AeropuertoDto(null, aeropuertoRequest.nombre(), aeropuertoRequest.ciudad(), aeropuertoRequest.pais());
        AeropuertoDto newAeropuerto = aeropuertoService.guardarAeropuerto(aeropuertoDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAeropuerto.idAeropuerto())
                .toUri();
        return ResponseEntity.created(location).body(newAeropuerto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AeropuertoDto> updateAeropuerto(@PathVariable Long id, @Valid @RequestBody AeropuertoUpdateRequest aeropuertoRequest) {
        AeropuertoDto aeropuertoDto = new AeropuertoDto(null, aeropuertoRequest.nombre(), aeropuertoRequest.ciudad(), aeropuertoRequest.pais());
        Optional<AeropuertoDto> aeropuertoUpdated = aeropuertoService.actualizarAeropuerto(id, aeropuertoDto);
        return aeropuertoUpdated.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    AeropuertoDto createdAeropuerto = aeropuertoService.guardarAeropuerto(aeropuertoDto);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdAeropuerto.idAeropuerto())
                            .toUri();
                    return ResponseEntity.created(location).body(createdAeropuerto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id) {
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }
}