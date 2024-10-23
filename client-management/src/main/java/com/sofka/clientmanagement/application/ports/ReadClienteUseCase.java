package com.sofka.clientmanagement.application.ports;

import com.sofka.clientmanagement.domain.dto.ClienteDto;

import java.util.List;

public interface ReadClienteUseCase {
    /**
    * Read a client
    * @param id the client id to read
    * @return ClienteDto the client
     */
    ClienteDto readCliente(Long id);

    /**
     * Read all clients
     * @return List<ClienteDto> the clients
     */
    List<ClienteDto> readAllCliente();
}
