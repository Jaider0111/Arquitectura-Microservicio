package com.sofka.transactions.application.ports;

import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.dto.MovimientoFilterDto;

import java.util.List;

public interface ReadMovimientoUseCase {
    /**
    * Read a movimiento
    * @param id the movimiento id to read
    * @return MovimientoDto the movimiento
     */
    MovimientoDto readMovimiento(Long id);

    /**
     * Read all movimientos
     * @return List<MovimientoDto> the list of movimientos
     */
    List<MovimientoDto> readAllMovimientos();
}
