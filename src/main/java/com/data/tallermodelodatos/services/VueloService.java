package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.dto.VueloCreateRequest;
import com.data.tallermodelodatos.dto.VueloUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface VueloService {
    /**
     * Crear un nuevo vuelo
     */
    VueloDto crearVuelo(VueloCreateRequest request);

    /**
     * Obtener vuelo por ID
     */
    VueloDto obtenerVueloPorId(Long id);

    /**
     * Listar todos los vuelos
     */
    List<VueloDto> listarVuelos();

    /**
     * Buscar vuelos por origen
     */
    List<VueloDto> buscarVuelosPorOrigen(String origen);

    /**
     * Buscar vuelos por destino
     */
    List<VueloDto> buscarVuelosPorDestino(String destino);

    /**
     * Actualizar vuelo existente
     */
    VueloDto actualizarVuelo(Long id, VueloUpdateRequest request);

    /**
     * Eliminar vuelo
     */
    void eliminarVuelo(Long id);
}