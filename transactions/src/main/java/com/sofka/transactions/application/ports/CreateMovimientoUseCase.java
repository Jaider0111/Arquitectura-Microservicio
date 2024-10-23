package com.sofka.transactions.application.ports;


import com.sofka.transactions.domain.dto.MovimientoDto;

public interface CreateMovimientoUseCase {

    /**
    * Create a new movimiento
    * @param movimientoDto the movimiento to create
    * @return the movimiento id
     */
    Long createMovimiento(MovimientoDto movimientoDto);
}
