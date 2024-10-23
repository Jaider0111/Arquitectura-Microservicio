package com.sofka.transactions.application.ports;

import com.sofka.transactions.domain.dto.CuentaDto;

public interface CreateCuentaUseCase {

    /**
    * Create a new account
    * @param cuentaDto the account to create
    * @return the account id
     */
    Long createCuenta(CuentaDto cuentaDto);
}
