package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteCreateRequest;
import com.data.tallermodelodatos.dto.ClienteUpdateRequest;
import com.data.tallermodelodatos.dto.ClienteMapper;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.exception.ClientNotFoundException;
import com.data.tallermodelodatos.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDto crearCliente(ClienteCreateRequest request) {
        Cliente cliente = clienteMapper.clienteCreateRequestToCliente(request);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDto(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDto obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));
        return clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> buscarClientesPorNombre(String nombre) {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteUpdateRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));

        cliente.setNombre(request.nombre());
        cliente.setApellido(request.apellido());
        cliente.setEmail(request.email());
        cliente.setUsername(request.username());
        cliente.setDireccion(request.direccion());
        cliente.setTelefono(request.telefono());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDto(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));
        clienteRepository.delete(cliente);
    }
}