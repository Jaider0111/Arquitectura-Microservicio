package com.sofka.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.sofka.transactions.domain.model.Movimiento}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto implements Serializable {
    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Long cuentaId;
}