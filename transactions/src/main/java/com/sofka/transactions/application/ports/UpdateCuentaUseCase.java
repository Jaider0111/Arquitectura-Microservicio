package com.sofka.transactions.application.ports;

import com.sofka.transactions.domain.dto.CuentaDto;

public interface UpdateCuentaUseCase {
    /**
    * Update a cuenta
    * @param id the cuenta id to update
    * @param cuentaDto the cuenta to update
    * @return CuentaDto the updated cuenta
    */
    CuentaDto updateCuenta(Long id, CuentaDto cuentaDto);

    /**
    * Update a cuenta partial
    * @param id the cuenta id to update
    * @param cuentaDto the cuenta to update
    * @return CuentaDto the updated cuenta
     */
    CuentaDto updateCuentaPartial(Long id, CuentaDto cuentaDto);
}
