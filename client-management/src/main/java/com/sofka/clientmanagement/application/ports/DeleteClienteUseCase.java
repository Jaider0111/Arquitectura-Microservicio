package com.sofka.clientmanagement.application.ports;

public interface DeleteClienteUseCase {
    /**
    * Delete a client
    * @param id the client id to delete
    * @return void
     */
    void  deleteCliente(Long id);
}
