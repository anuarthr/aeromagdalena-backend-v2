package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.AerolineaCreateRequest;
import com.data.tallermodelodatos.dto.AerolineaDto;
import com.data.tallermodelodatos.dto.AerolineaUpdateRequest;
import com.data.tallermodelodatos.services.AerolineaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
@CrossOrigin(origins = "http://localhost:5173")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @GetMapping()
    public ResponseEntity<List<AerolineaDto>> getAllAerolineas() {
        return ResponseEntity.ok(aerolineaService.buscarAerolineas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AerolineaDto> getAerolineaById(@PathVariable Long id) {
        return aerolineaService.buscarAerolineaPorId(id)
                .map(aerolinea -> ResponseEntity.ok().body(aerolinea))
                .orElseThrow(() -> new RuntimeException("Aerolinea not found"));
    }

    @PostMapping()
    public ResponseEntity<AerolineaDto> createAerolinea(@Valid @RequestBody AerolineaCreateRequest aerolineaRequest) throws URISyntaxException {
        AerolineaDto aerolineaDto = new AerolineaDto(null, aerolineaRequest.nombre(), aerolineaRequest.codigoAerolinea(), aerolineaRequest.paisDeOrigen());
        AerolineaDto newAerolinea = aerolineaService.guardarAerolinea(aerolineaDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAerolinea.idAerolinea())
                .toUri();
        return ResponseEntity.created(location).body(newAerolinea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AerolineaDto> updateAerolinea(@PathVariable Long id, @Valid @RequestBody AerolineaUpdateRequest aerolineaRequest) {
        AerolineaDto aerolineaDto = new AerolineaDto(null, aerolineaRequest.nombre(), aerolineaRequest.codigoAerolinea(), aerolineaRequest.paisDeOrigen());
        Optional<AerolineaDto> aerolineaUpdated = aerolineaService.actualizarAerolinea(id, aerolineaDto);
        return aerolineaUpdated.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    AerolineaDto createdAerolinea = aerolineaService.guardarAerolinea(aerolineaDto);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdAerolinea.idAerolinea())
                            .toUri();
                    return ResponseEntity.created(location).body(createdAerolinea);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
        aerolineaService.eliminarAerolinea(id);
        return ResponseEntity.noContent().build();
    }
}