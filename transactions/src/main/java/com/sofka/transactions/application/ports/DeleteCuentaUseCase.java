package com.sofka.transactions.application.ports;

public interface DeleteCuentaUseCase {
    /**
    * Delete a cuenta
    * @param id the cuenta id to delete
    * @return void
     */
    void deleteCuenta(Long id);
}
