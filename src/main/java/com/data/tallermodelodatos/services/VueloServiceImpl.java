package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloMapper;
import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.dto.VueloCreateRequest;
import com.data.tallermodelodatos.dto.VueloUpdateRequest;
import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;
import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.exception.AerolineaNotFoundException;
import com.data.tallermodelodatos.exception.AeropuertoNotFoundException;
import com.data.tallermodelodatos.exception.VueloNotFoundException;
import com.data.tallermodelodatos.repositories.AerolineaRepository;
import com.data.tallermodelodatos.repositories.AeropuertoRepository;
import com.data.tallermodelodatos.repositories.VueloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;
    private final AerolineaRepository aerolineaRepository;
    private final AeropuertoRepository aeropuertoRepository;

    @Override
    public VueloDto crearVuelo(VueloCreateRequest request) {
        // Validar que aerolínea y aeropuerto existan
        Aerolinea aerolinea = aerolineaRepository.findById(request.aerolineaId())
                .orElseThrow(() -> new AerolineaNotFoundException("Aerolínea no encontrada con ID: " + request.aerolineaId()));
        Aeropuerto aeropuerto = aeropuertoRepository.findById(request.aeropuertoId())
                .orElseThrow(() -> new AeropuertoNotFoundException("Aeropuerto no encontrado con ID: " + request.aeropuertoId()));

        // Mapear y crear vuelo
        Vuelo vuelo = vueloMapper.vueloCreateRequestToVuelo(request);
        vuelo.setAerolinea(aerolinea);
        vuelo.setAeropuerto(aeropuerto);

        Vuelo vueloGuardado = vueloRepository.save(vuelo);
        return vueloMapper.vueloToVueloDto(vueloGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public VueloDto obtenerVueloPorId(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException("Vuelo no encontrado con ID: " + id));
        return vueloMapper.vueloToVueloDto(vuelo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> listarVuelos() {
        return vueloRepository.findAll()
                .stream()
                .map(vueloMapper::vueloToVueloDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> buscarVuelosPorOrigen(String origen) {
        return vueloRepository.findByOrigen(origen)
                .stream()
                .map(vueloMapper::vueloToVueloDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDto> buscarVuelosPorDestino(String destino) {
        return vueloRepository.findByDestino(destino)
                .stream()
                .map(vueloMapper::vueloToVueloDto)
                .collect(Collectors.toList());
    }

    @Override
    public VueloDto actualizarVuelo(Long id, VueloUpdateRequest request) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException("Vuelo no encontrado con ID: " + id));

        // Validar que aerolínea y aeropuerto existan
        Aerolinea aerolinea = aerolineaRepository.findById(request.aerolineaId())
                .orElseThrow(() -> new AerolineaNotFoundException("Aerolínea no encontrada con ID: " + request.aerolineaId()));
        Aeropuerto aeropuerto = aeropuertoRepository.findById(request.aeropuertoId())
                .orElseThrow(() -> new AeropuertoNotFoundException("Aeropuerto no encontrado con ID: " + request.aeropuertoId()));

        // Actualizar campos
        vuelo.setOrigen(request.origen());
        vuelo.setDestino(request.destino());
        vuelo.setFechaDeSalida(request.fechaDeSalida());
        vuelo.setDuracion(request.duracion());
        vuelo.setCapacidad(request.capacidad());
        vuelo.setAerolinea(aerolinea);
        vuelo.setAeropuerto(aeropuerto);

        Vuelo vueloActualizado = vueloRepository.save(vuelo);
        return vueloMapper.vueloToVueloDto(vueloActualizado);
    }

    @Override
    public void eliminarVuelo(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException("Vuelo no encontrado con ID: " + id));
        vueloRepository.delete(vuelo);
    }
}