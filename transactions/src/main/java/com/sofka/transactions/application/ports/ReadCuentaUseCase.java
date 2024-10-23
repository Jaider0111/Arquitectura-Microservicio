package com.sofka.transactions.application.ports;

import com.sofka.transactions.domain.dto.CuentaDto;

import java.util.List;

public interface ReadCuentaUseCase {
    /**
    * Read a cuenta
    * @param id the cuenta id to read
    * @return CuenteDto the cuenta
     */
    CuentaDto readCuenta(Long id);

    /**
     * Read all cuenta
     * @return List<ClienteDto> the list of all cuenta
     */
    List<CuentaDto> readAllCuenta();
}
