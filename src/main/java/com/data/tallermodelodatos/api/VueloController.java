package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.dto.VueloCreateRequest;
import com.data.tallermodelodatos.dto.VueloUpdateRequest;
import com.data.tallermodelodatos.services.VueloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelos")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class VueloController {

    private final VueloService vueloService;

    /**
     * GET /api/v1/vuelos - Listar todos los vuelos
     */
    @GetMapping
    public ResponseEntity<List<VueloDto>> listarVuelos() {
        List<VueloDto> vuelos = vueloService.listarVuelos();
        return ResponseEntity.ok(vuelos);
    }

    /**
     * GET /api/v1/vuelos/{id} - Obtener vuelo por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<VueloDto> obtenerVueloPorId(@PathVariable Long id) {
        VueloDto vuelo = vueloService.obtenerVueloPorId(id);
        return ResponseEntity.ok(vuelo);
    }

    /**
     * POST /api/v1/vuelos - Crear nuevo vuelo
     */
    @PostMapping
    public ResponseEntity<VueloDto> crearVuelo(@Valid @RequestBody VueloCreateRequest request) {
        VueloDto vueloCreado = vueloService.crearVuelo(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vueloCreado.idVuelo())
                .toUri();
        return ResponseEntity.created(location).body(vueloCreado);
    }

    /**
     * PUT /api/v1/vuelos/{id} - Actualizar vuelo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<VueloDto> actualizarVuelo(
            @PathVariable Long id,
            @Valid @RequestBody VueloUpdateRequest request) {
        VueloDto vueloActualizado = vueloService.actualizarVuelo(id, request);
        return ResponseEntity.ok(vueloActualizado);
    }

    /**
     * DELETE /api/v1/vuelos/{id} - Eliminar vuelo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable Long id) {
        vueloService.eliminarVuelo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/vuelos/buscar/origen - Buscar vuelos por origen
     */
    @GetMapping("/buscar/origen")
    public ResponseEntity<List<VueloDto>> buscarPorOrigen(@RequestParam String origen) {
        List<VueloDto> vuelos = vueloService.buscarVuelosPorOrigen(origen);
        return ResponseEntity.ok(vuelos);
    }

    /**
     * GET /api/v1/vuelos/buscar/destino - Buscar vuelos por destino
     */
    @GetMapping("/buscar/destino")
    public ResponseEntity<List<VueloDto>> buscarPorDestino(@RequestParam String destino) {
        List<VueloDto> vuelos = vueloService.buscarVuelosPorDestino(destino);
        return ResponseEntity.ok(vuelos);
    }
}
