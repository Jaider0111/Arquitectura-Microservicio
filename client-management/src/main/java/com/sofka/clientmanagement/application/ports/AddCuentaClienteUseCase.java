package com.sofka.clientmanagement.application.ports;


public interface AddCuentaClienteUseCase {
    /**
    * Add a new account to a client
    * @param numeroCuenta the account number
    * @param clienteId the client id
     */
    void addCuentaCliente(String numeroCuenta, Long clienteId);
}
