package com.sofka.clientmanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid", nullable = false)
    private Long clienteId;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @OneToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
}