package com.sofka.clientmanagement.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.sofka.clientmanagement.domain.model.Cliente}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto extends PersonaDto implements Serializable {

    public ClienteDto(Long id, String nombre, String genero, int edad, String identificacion, String direccion, String telefono, Long clienteId, String contrasena, Boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    private Long clienteId;
    private String contrasena;
    private Boolean estado;
}