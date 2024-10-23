package com.sofka.clientmanagement.application.ports;

import com.sofka.clientmanagement.domain.dto.ClienteDto;

public interface CreateClienteUseCase {
    /**
    * Create a new client
    * @param clienteDto the client to create
    * @return the client id
     */
    Long createCliente(ClienteDto clienteDto);
}
