package com.sofka.transactions.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoFilterDto {
    Long idCliente;
    LocalDate fechaInicio;
    LocalDate fechaFin;
}
