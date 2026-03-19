package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteCreateRequest;
import com.data.tallermodelodatos.dto.ClienteUpdateRequest;
import com.data.tallermodelodatos.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * GET /api/v1/clientes - Listar todos los clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteDto> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * GET /api/v1/clientes/{id} - Obtener cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id) {
        ClienteDto cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * POST /api/v1/clientes - Crear nuevo cliente
     */
    @PostMapping
    public ResponseEntity<ClienteDto> crearCliente(@Valid @RequestBody ClienteCreateRequest request) {
        ClienteDto clienteCreado = clienteService.crearCliente(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteCreado.idCliente())
                .toUri();
        return ResponseEntity.created(location).body(clienteCreado);
    }

    /**
     * PUT /api/v1/clientes/{id} - Actualizar cliente existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteUpdateRequest request) {
        ClienteDto clienteActualizado = clienteService.actualizarCliente(id, request);
        return ResponseEntity.ok(clienteActualizado);
    }

    /**
     * DELETE /api/v1/clientes/{id} - Eliminar cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/clientes/buscar/nombre - Buscar clientes por nombre
     */
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ClienteDto>> buscarPorNombre(@RequestParam String nombre) {
        List<ClienteDto> clientes = clienteService.buscarClientesPorNombre(nombre);
        return ResponseEntity.ok(clientes);
    }
}