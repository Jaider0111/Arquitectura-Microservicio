package com.sofka.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.sofka.transactions.domain.model.Cuenta}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto implements Serializable {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;
}