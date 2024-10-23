package com.sofka.transactions.application.ports;

public interface DeleteMovimientoUseCase {
    /**
    * Delete a movimiento
    * @param id the movimiento id to delete
     */
    void deleteMovimiento(Long id);
}
