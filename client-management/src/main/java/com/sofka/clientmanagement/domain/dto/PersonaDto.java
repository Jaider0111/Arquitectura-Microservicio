package com.sofka.clientmanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.sofka.clientmanagement.domain.model.Persona}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto implements Serializable {
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}