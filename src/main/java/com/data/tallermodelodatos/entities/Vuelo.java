package com.data.tallermodelodatos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vuelos")
public class Vuelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVuelo;

    @NotBlank(message = "El origen del vuelo es obligatorio")
    @Column(nullable = false, length = 100)
    private String origen;

    @NotBlank(message = "El destino del vuelo es obligatorio")
    @Column(nullable = false, length = 100)
    private String destino;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fechaDeSalida;

    @NotNull(message = "La duración del vuelo es obligatoria")
    @Positive(message = "La duración debe ser mayor a 0")
    @Column(nullable = false)
    private Integer duracion; // en minutos

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aerolinea_id", nullable = false)
    @NotNull(message = "La aerolínea es obligatoria")
    private Aerolinea aerolinea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aeropuerto_id", nullable = false)
    @NotNull(message = "El aeropuerto es obligatorio")
    private Aeropuerto aeropuerto;

    @ManyToMany(mappedBy = "vuelos", fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
