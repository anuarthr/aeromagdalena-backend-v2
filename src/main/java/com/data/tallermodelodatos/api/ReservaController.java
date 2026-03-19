package com.data.tallermodelodatos.api;

// TODO: Refactorizar ReservaController siguiendo patrón de Vuelo y Cliente
// Este controller está temporalmente deshabilitado durante la migración de arquitectura

/*
import com.data.tallermodelodatos.dto.PasajeroDto;
import com.data.tallermodelodatos.dto.ReservaDto;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.exception.ReservaNotFoundException;
import com.data.tallermodelodatos.services.ClienteService;
import com.data.tallermodelodatos.services.PasajeroService;
import com.data.tallermodelodatos.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservas")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaController {

    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final PasajeroService pasajeroService;

    public ReservaController(ReservaService reservaService, ClienteService clienteService, PasajeroService pasajeroService) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.pasajeroService = pasajeroService;
    }

    @GetMapping()
    public ResponseEntity<List<ReservaDto>> getAllReservas() {
        return ResponseEntity.ok(reservaService.buscarReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable Long id) {
        return reservaService.buscarReservaPorId(id)
                .map(reserva -> ResponseEntity.ok().body(reserva))
                .orElseThrow(ReservaNotFoundException::new);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservaDto>> getReservasByClienteId(@PathVariable Long idCliente) {
        Cliente cliente = clienteService.buscarClienteEntityPorId(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        return ResponseEntity.ok(reservaService.buscarReservasPorCliente(cliente));
    }

    @PostMapping()
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) throws URISyntaxException {
        Cliente cliente = clienteService.buscarClienteEntityPorId(reservaDto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        ReservaDto newReserva = reservaService.guardarReserva(reservaDto, cliente);
        if (reservaDto.pasajeros() != null) {
            for (PasajeroDto pasajeroDto : reservaDto.pasajeros()) {
                pasajeroDto = new PasajeroDto(null, pasajeroDto.nombre(), pasajeroDto.apellido(), pasajeroDto.pasaporte(), pasajeroDto.nacionalidad());
                pasajeroService.guardarPasajero(pasajeroDto);
            }
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.idReserva())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long id, @RequestBody ReservaDto newReservaDto) {
        Optional<ReservaDto> reservaUpdated = reservaService.actualizarReserva(id, newReservaDto);
        return reservaUpdated.map(reserva -> ResponseEntity.ok(reserva))
                .orElseGet(() -> {
                    Cliente cliente = clienteService.buscarClienteEntityPorId(newReservaDto.idCliente())
                            .orElseThrow(() -> new RuntimeException("Cliente not found"));
                    ReservaDto createdReserva = reservaService.guardarReserva(newReservaDto, cliente);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdReserva.idReserva())
                            .toUri();
                    return ResponseEntity.created(location).body(createdReserva);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
*/
