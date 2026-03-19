package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteCreateRequest;
import com.data.tallermodelodatos.dto.ClienteUpdateRequest;
import com.data.tallermodelodatos.entities.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
     * Obtener entidad Cliente por ID (para uso interno)
     */
    Optional<Cliente> obtenerClienteEntity(Long id);

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