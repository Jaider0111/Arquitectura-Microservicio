package com.sofka.transactions.application.ports;


import com.sofka.transactions.domain.dto.MovimientoDto;

public interface UpdateMovimientoUseCase {
    /**
    * Update a movimiento
    * @param id the movimiento id to update
    * @param movimientoDto the movimiento to update
    * @return MovimientoDto the updated movimiento
     */
    MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto);

    /**
    * Update a movimiento partial
    * @param id the movimiento id to update
    * @param movimientoDto the movimiento to update
    * @return MovimientoDto the updated movimiento
     */
    MovimientoDto updateMovimientoPartial(Long id, MovimientoDto movimientoDto);
}
