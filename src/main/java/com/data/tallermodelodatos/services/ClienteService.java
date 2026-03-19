package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteCreateRequest;
import com.data.tallermodelodatos.dto.ClienteUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ClienteService {
    /**
     * Crear un nuevo cliente
     */
    ClienteDto crearCliente(ClienteCreateRequest request);

    /**
     * Obtener cliente por ID
     */
    ClienteDto obtenerClientePorId(Long id);

    /**
     * Listar todos los clientes
     */
    List<ClienteDto> listarClientes();

    /**
     * Buscar clientes por nombre
     */
    List<ClienteDto> buscarClientesPorNombre(String nombre);

    /**
     * Actualizar cliente existente
     */
    ClienteDto actualizarCliente(Long id, ClienteUpdateRequest request);

    /**
     * Eliminar cliente
     */
    void eliminarCliente(Long id);
}