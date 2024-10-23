package com.sofka.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMovimientoDto {
    private Long id;
    LocalDateTime fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private Boolean estado;
    private String tipoMovimiento;
    private Double valor;
    private Double movimiento;
    private Double saldoInicial;
    private Double saldoFinal;
}
